/**
 * Copyright (c) 2002-2012 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.ecore.plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.osgi.framework.BundleContext;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.ContributorFactorySimple;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.RegistryFactory;
import org.eclipse.core.runtime.spi.IRegistryProvider;
import org.eclipse.core.runtime.spi.RegistryStrategy;

import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EPackageRegistryImpl;
import org.eclipse.emf.ecore.resource.URIConverter;


/**
 * A collection of platform-neutral static utilities
 * as well as Eclipse support utilities.
 */
public class EcorePlugin  extends EMFPlugin
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
    super(new ResourceLocator[] {});
  }

  @Override
  public ResourceLocator getPluginResourceLocator()
  {
    return plugin;
  }

  /**
   * Returns the platform resource map.
   * <p>
   * This map is from {@link String} to {@link URI}.
   * It is the logical equivalent of the map implied by an {@link IWorkspaceRoot}:
   * I.e., each entry in the map corresponds to
   * an {@link org.eclipse.core.resources.IProject} 
   * that has a {@link org.eclipse.core.resources.IResource#getName name} 
   * and a location {@link org.eclipse.core.resources.IResource#getLocation location};
   * the name is the key 
   * and the location, interpreted as a {@link URI#createFileURI file URI}, is the value.
   * This map is used to {@link #resolvePlatformResourcePath resolve} a platform resource path,
   * and thereby supports relocatable projects in a manner that is transparently the same as an Eclipse workspace.
   * </p>
   * @return the platform resource map.
   * @see #resolvePlatformResourcePath
   */
  public static Map<String, URI> getPlatformResourceMap()
  {
    if (platformResourceMap == null)
    {
      platformResourceMap = new HashMap<String, URI>();
    }
    return platformResourceMap;
  }

  /**
   * Resolves a platform resource path of the form <code>"/project/path"</code> 
   * against the platform resource map.
   * <p>
   * The first segment of the path, i.e., the <em>project name</em>,
   * is used to get a URI from the {@link #getPlatformResourceMap() map}.
   * If a URI results, the remaining segments are {@link URI#resolve(URI) resolved} against it
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
    if (platformResourceMap != null)
    {
      int index = platformResourcePath.indexOf("/", 1);
      String rootContainerName = platformResourcePath.substring(1, index);
      String relativeName = platformResourcePath.substring(index + 1);
      URI rootContainerLocation = getPlatformResourceMap().get(rootContainerName);
      if (rootContainerLocation != null)
      {
        return URI.createURI(relativeName).resolve(rootContainerLocation);
      }
    }
    return null;
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
   * Returns a map from {@link EPackage#getNsURI() package namespace URI} (represented as a String) 
   * to the location of the GenModel containing a GenPackage for the package (represented as a {@link URI URI}).
   * <p>
   * It's implemented like this:
   *<pre>
   *  return getEPackageNsURIToGenModelLocationMap(false);
   *</pre>
   * </p>
   * @return a map from package namespace to GenModel location.
   * @deprecated since 2.9;
   * use {@link #getEPackageNsURIToGenModelLocationMap(boolean) getEPackageNsURItoGenModelLocationMap(true)}
   * to get the locations in the target platform, 
   * or use {@link #getEPackageNsURIToGenModelLocationMap(boolean) getEPackageNsURItoGenModelLocationMap(false)} to get the legacy behavior, i.e., the locations in the installed environment.
   * It's generally expected that all clients, will migrate to use the target platform.
   */
  @Deprecated
  public static Map<String, URI> getEPackageNsURIToGenModelLocationMap()
  {
    return getEPackageNsURIToGenModelLocationMap(false);
  }

  /**
   * Returns a map from {@link EPackage#getNsURI() package namespace URI} (represented as a String) 
   * to the location of the GenModel containing a GenPackage for the package (represented as a {@link URI URI})
   * for either the target platform or the environment itself.
   * If there is no target platform, i.e., if the PDE is not installed, it defaults back to the environment.
   * It's generally expected that an application using these URIs will also {@link URIConverter#getURIMap() register} the mappings returned by {@link #computePlatformURIMap(boolean)}.
   * @param targetPlatform whether to get locations for the target platform or for the environment itself; the former is preferred.
   * @return a map from package namespace to GenModel location.
   * @see #computePlatformURIMap(boolean)
   * @since 2.9
   */
  public static Map<String, URI> getEPackageNsURIToGenModelLocationMap(boolean targetPlatform)
  {
    if (!targetPlatform || !IS_RESOURCES_BUNDLE_AVAILABLE || !PDEHelper.IS_PDE_BUNDLE_AVAILABLE)
    {
      if (ePackageNsURIToGenModelLocationMap == null)
      {
        ePackageNsURIToGenModelLocationMap = new HashMap<String, URI>();
      }
      return ePackageNsURIToGenModelLocationMap;
    }
    else
    {
      Map<String, URI> nsURIMap = new HashMap<String, URI>();
      try
      {
        PDEHelper.computeModels(null, nsURIMap);
      }
      catch (Exception exception)
      {
        INSTANCE.log(exception);
      }
      return nsURIMap;
    }
  }

  /**
   * Computes a map from <code>platform:/resource/&lt;plugin-location>/</code> {@link URI} to 
   * <code>platform:/plugin/&lt;plugin-id>/</code> URI
   * for each URI in the collection of the form <code>platform:/plugin/&lt;plugin-id>/...</code>.
   * This allows each plugin to be {@link org.eclipse.emf.ecore.resource.URIConverter#getURIMap() treated} 
   * as if it were a project in the workspace.
   * If the workspace or {@link #getPlatformResourceMap() platform resource map} already contains a project for the plugin location, no such mapping is produced.
   * In addition, when running stand alone with {@link ExtensionProcessor#process(ClassLoader) extension processing} enabled,
   * mappings from <code>platform:/plugin/&lt;plugin-id>/</code> to the physical location of the plugin's archive or root folder are produced.
   * This allows the URIs to be loaded from their proper physical location.
   * @param uris a collections of {@link URI}s.
   * @return a map from platform resource URI to platform plugin URI.
   */
  public static Map<URI, URI> computePlatformResourceToPlatformPluginMap(Collection<URI> uris)
  {
    Map<URI, URI> result = new HashMap<URI, URI>();
    IWorkspaceRoot root = getWorkspaceRoot();
    if (root != null)
    {
      for (URI uri : uris)
      {
        if (uri.isPlatformPlugin())
        {
          String pluginID = uri.segment(1);
          if (!root.getProject(pluginID).isOpen())
          {
            result.put(URI.createPlatformResourceURI(pluginID + "/", false), URI.createPlatformPluginURI(pluginID + "/", false));
          }
        }
      }
    }
    else if (platformResourceMap != null || ExtensionProcessor.platformPluginToLocationURIMap != null)
    {
      for (URI uri : uris)
      {
        if (uri.isPlatformPlugin())
        {
          String pluginID = uri.segment(1);
          if (platformResourceMap == null || !platformResourceMap.containsKey(pluginID))
          {
            URI platformPluginURI = uri.trimSegments(uri.segmentCount() - 2).appendSegment("");
            URI platformResourceURI = URI.createPlatformResourceURI(platformPluginURI.segment(1), false).appendSegment("");
            result.put(platformResourceURI, platformPluginURI);

            if (ExtensionProcessor.platformPluginToLocationURIMap != null)
            {
              URI locationURI = ExtensionProcessor.platformPluginToLocationURIMap.get(platformPluginURI);
              if (locationURI != null)
              {
                result.put(platformPluginURI, locationURI);
              }
            }
          }
        }
      }
    }
    return result;
  }

  private static Pattern bundleSymbolNamePattern;
  private static byte [] NO_BYTES = new byte [0];
  
  /**
   * Computes a map from <code>platform:/plugin/&lt;plugin-id>/</code> {@link URI} to 
   * <code>platform:/resource/&lt;plugin-location>/</code> URI
   * for each plugin project in the workspace or {@link #getPlatformResourceMap() platform resource map}.
   * This allows each plugin from the runtime to be {@link org.eclipse.emf.ecore.resource.URIConverter#getURIMap() redirected} 
   * to its active version in the workspace or platform resource map.
   * @return a map from plugin URIs to resource URIs.
   * @see org.eclipse.emf.ecore.resource.URIConverter#getURIMap()
   * @see URI
   */
  public static Map<URI, URI> computePlatformPluginToPlatformResourceMap()
  {
    Map<URI, URI> result = new HashMap<URI, URI>();
    IWorkspaceRoot root = getWorkspaceRoot();
    if (root != null)
    {
      IProject [] projects = root.getProjects();
      if (projects != null)
      {
        String pluginID = null;
        
        class Handler extends DefaultHandler
        {
          public String pluginID;

          @Override
          public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
          {
            if ("".equals(uri) && "plugin".equals(localName))
            {
              pluginID = attributes.getValue("id");
            }
            throw new SAXException("Done");
          }
        }
        Handler handler = new Handler();
        
        SAXParserFactory parserFactory= SAXParserFactory.newInstance();
        parserFactory.setNamespaceAware(true);
        SAXParser parser = null;
        
        try
        {
          parser = parserFactory.newSAXParser();
        }
        catch (Exception exception)
        {
          INSTANCE.log(exception);
        }
        
        if (bundleSymbolNamePattern == null)
        {
          bundleSymbolNamePattern = Pattern.compile("^\\s*Bundle-SymbolicName\\s*:\\s*([^\\s;]*)\\s*(;.*)?$", Pattern.MULTILINE);
        }
        
        byte [] bytes = NO_BYTES;
        
        for (int i = 0, size = projects.length; i < size; ++i)
        {
          IProject project = projects[i];
          if (project.isOpen())
          {
            pluginID = null;
            IFile manifest = project.getFile("META-INF/MANIFEST.MF");
            if (manifest.exists())
            {
              InputStream inputStream = null;
              try
              {
                inputStream = manifest.getContents(true); 
                int available = inputStream.available();
                if (bytes.length < available)
                {
                  bytes = new byte [available];
                }
                inputStream.read(bytes);
                String contents = new String(bytes, "UTF-8");
                Matcher matcher = bundleSymbolNamePattern.matcher(contents);
                if (matcher.find())
                {
                  pluginID = matcher.group(1);
                }
              }
              catch (Exception exception)
              {
                INSTANCE.log(exception);
              }
              finally
              {
                if (inputStream != null)
                {
                  try
                  {
                    inputStream.close();
                  }
                  catch (IOException exception)
                  {
                    INSTANCE.log(exception);
                  }
                }
              }
            }
            else if (parser != null)
            {
              final IFile plugin = project.getFile("plugin.xml");
              if (plugin.exists())
              {
                try
                {
                  parser.parse(new InputSource(plugin.getContents(true)), handler);
                }
                catch (Exception exception)
                {
                  if (handler.pluginID != null)
                  {
                    pluginID = handler.pluginID;
                  }
                  else
                  {
                    INSTANCE.log(exception);
                  }
                }
              }
            }
            
            if (pluginID != null)
            {
              URI platformPluginURI = URI.createPlatformPluginURI(pluginID + "/", false);
              URI platformResourceURI = URI.createPlatformResourceURI(project.getName() + "/",  true);
              result.put(platformPluginURI, platformResourceURI);
            }
          }
        }
      }
    }
    else if (platformResourceMap != null)
    {
      for (Map.Entry<String, URI> entry : platformResourceMap.entrySet())
      {
        String pluginID = entry.getKey();
        URI platformPluginURI = URI.createPlatformPluginURI(pluginID, true).appendSegment("");
        URI platformResourceURI = URI.createPlatformResourceURI(pluginID, true).appendSegment("");
        result.put(platformPluginURI, platformResourceURI);
      }
    }

    return result;
  }
  
  /**
   * Computes a map so that plugins in the workspace will override those in the target platform or the environment
   * and so that plugins with Ecore and GenModels in the target platform or the environment will look like projects in the workspace.
   * It's implemented like this:
   *<pre>
   *  return computePlatformURIMap(false);
   *</pre>
   * @deprecated since 2.9;
   * use {@link #computePlatformURIMap(boolean) computePlatformURIMap(true)}
   * to get the mappings for the target platform, 
   * or use {@link #computePlatformURIMap(boolean) computePlatformURIMap(false)} to get the legacy behavior, i.e., the mappings for the installed environment.
   * It's generally expected that all clients, will migrate to use the target platform.
   * @return computes a map so that plugins in the workspace will override those in the environment
   * and so that plugins with Ecore and GenModels will look like projects in the workspace.
   * @see org.eclipse.emf.ecore.resource.URIConverter#getURIMap()
   * @see #computePlatformPluginToPlatformResourceMap()
   * @see #computePlatformResourceToPlatformPluginMap(Collection)
   */
  @Deprecated
  public static Map<URI, URI> computePlatformURIMap()
  {
    return computePlatformURIMap(false);
  }

  /**
   * Computes a map so that plugins in the workspace will override those in the target platform or the environment
   * and so that plugins with Ecore and GenModels in the target platform or the environment will look like projects in the workspace.
   * @return computes a map so that plugins in the workspace will override those in the target platform of the environment
   * and so that plugins in the target platform or environment with Ecore and GenModels will look like projects in the workspace.
   * @see org.eclipse.emf.ecore.resource.URIConverter#getURIMap()
   * @see #computePlatformPluginToPlatformResourceMap()
   * @see #computePlatformResourceToPlatformPluginMap(Collection)
   * @since 2.9
   */
  public static Map<URI, URI> computePlatformURIMap(boolean targetPlatform)
  {
    Map<URI, URI> result = new HashMap<URI, URI>();
    if (!targetPlatform || !IS_RESOURCES_BUNDLE_AVAILABLE || !PDEHelper.IS_PDE_BUNDLE_AVAILABLE)
    {
      result.putAll(computePlatformPluginToPlatformResourceMap());
      result.putAll(computePlatformResourceToPlatformPluginMap(new HashSet<URI>(EcorePlugin.getEPackageNsURIToGenModelLocationMap(false).values())));
    }
    else
    {
      try
      {
        Map<URI, URI> pluginMap = new HashMap<URI, URI>();
        PDEHelper.computeModels(pluginMap, null);
        result.putAll(pluginMap);
      }
      catch (Exception e)
      {
        INSTANCE.log(e);
      }
    }
    return result;
  }
  
  /**
   * The platform resource map.
   * @see #getPlatformResourceMap
   */
  private static Map<String, URI> platformResourceMap;
  
  /**
   * The map from package namespace URIs to the location of the GenModel for that package.
   * @see #getPlatformResourceMap
   */
  private static Map<String, URI> ePackageNsURIToGenModelLocationMap;

  /** 
   * A plugin implementation that handles Ecore plugin registration.
   * @see #startup()
   */
  static public class Implementation extends EclipsePlugin
  {
    /**
     * Creates the singleton instance.
     */
    public Implementation()
    {
      super();
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
     * @throws Exception if there is a show stopping problem.
     */
    @Override
    public void start(BundleContext context) throws Exception
    {
      super.start(context);
      ExtensionProcessor.internalProcessExtensions();
      
    }
  }

  /**
   * A class containing a single utility method for processing extensions.
   * @see ExtensionProcessor#process(ClassLoader)
   * @since 2.9
   */
  public static class ExtensionProcessor
  {
    private static Map<URI, URI> platformPluginToLocationURIMap;

    
    /**
     * This explicitly triggers processing of all plugin.xml registered extensions.
     * It does nothing if invoked in the context of an Eclipse application as processing of extensions happens implicitly during {@link Implementation#start(BundleContext) bundle activation}.
     * As such this method is useful only in non-Eclipse applications to ensure that plugin.xml registrations are processed just as they are in an Eclipse application.
     * The exploit this mechanism, the classpath of the application must minimally include <code>org.eclipse.equinox.common</code>, <code>org.eclispe.equinox.registry</code>, and <code>org.eclipse.osgi</code>
     * <p>
     * This method creates a registry if {@link RegistryFactory#getRegistry() one does not already exist}.
     * It will first consider all entries on the classpath as provided by {@link System#getProperty(String) System.getProperty("java.class.path")}
     * to determine if there are any folder entries whose parent folder contains a plugin.xml;
     * such entries are common when launching a Java application from Eclipse at development time.
     * In that case, the class loader is not used and only registrations for entries of the classpath are considered.
     * Otherwise, the class loader is used to find all plugin.xml resources and only those entries are considered.
     * </p>
     * <p>
     * </p>
     * @param classLoader the class loader used to locate plugin.xml resources; it may be null in which class the {@link Thread#getContextClassLoader() current thread's context class loader} is used, if necessary.
     * @since 2.9
     */
    public static synchronized void process(ClassLoader classLoader)
    {
      // Ensure processing only happens once and only when not running an Eclipse application.
      //
      if (platformPluginToLocationURIMap == null && !IS_ECLIPSE_RUNNING)
      {
        platformPluginToLocationURIMap = new HashMap<URI, URI>();

        // If there isn't already a registry...
        //
        IExtensionRegistry registry = RegistryFactory.getRegistry();
        if (registry == null)
        {
          // Create a new registry.
          //
          final IExtensionRegistry newRegistry =
            RegistryFactory.createRegistry
              (new RegistryStrategy(null, null)
               {
                 @Override
                 public void log(IStatus status)
                 {
                   INSTANCE.log(status);
                 }

                 @Override
                 public String translate(String key, ResourceBundle resources)
                 {
                   try
                   {
                     // The org.eclipse.core.resources bundle has keys that aren't translated, so avoid exception propagation.
                     //
                     return super.translate(key, resources);
                   }
                   catch (Throwable throwable)
                   {
                     return key;
                   }
                 }
               },
               null,
               null);

          // Make the new registry the default.
          //
          try
          {
            RegistryFactory.setDefaultRegistryProvider
              (new IRegistryProvider()
               {
                 public IExtensionRegistry getRegistry()
                 {
                  return newRegistry;
                 }
               });
          }
          catch (CoreException exception)
          {
            INSTANCE.log(exception);
          }

          registry = newRegistry;
        }

        // If there is no class loader provided, use the thread's context class loader.
        //
        if (classLoader == null)
        {
          classLoader = Thread.currentThread().getContextClassLoader();
        }

        // Process all the URIs for plugin.xml files from the class path or the class loader.
        //
        for (URI pluginXMLURI : getPluginXMLs(classLoader))
        {
          // Construct the URI for the manifest and check that it exists...
          //
          URI pluginLocation = pluginXMLURI.trimSegments(1);
          URI manifestURI = pluginLocation.appendSegments(new String[] { "META-INF", "MANIFEST.MF" });
          if (URIConverter.INSTANCE.exists(manifestURI, null))
          {
            InputStream manifestInputStream = null;
            try
            {
              // Read the manifest.
              //
              manifestInputStream = URIConverter.INSTANCE.createInputStream(manifestURI);
              Manifest manifest = new Manifest(manifestInputStream);
              java.util.jar.Attributes mainAttributes = manifest.getMainAttributes();

              // Determine the bundle's name
              //
              String bundleSymbolicName = mainAttributes.getValue("Bundle-SymbolicName");
              if (bundleSymbolicName != null)
              {
                // Split out the OSGi noise.
                //
                bundleSymbolicName = bundleSymbolicName.split(";")[0].trim();

                // Compute the map entry from platform:/plugin/<bundleSymbolicName>/ to the location URI's root.
                //
                platformPluginToLocationURIMap.put(URI.createPlatformPluginURI(bundleSymbolicName, true).appendSegment(""), pluginLocation.isArchive() ? pluginLocation : pluginLocation.appendSegment(""));

                // Find the localization resource bundle, if there is one.
                //
                String bundleLocalization = mainAttributes.getValue("Bundle-Localization");
                ResourceBundle resourceBundle = null;
                if (bundleLocalization != null)
                {
                  bundleLocalization += ".properties";
                  InputStream bundleLocalizationInputStream = URIConverter.INSTANCE.createInputStream(pluginLocation.appendSegment(bundleLocalization));
                  resourceBundle = new PropertyResourceBundle(bundleLocalizationInputStream);
                  bundleLocalizationInputStream.close();
                }

                // Add the contribution.
                //
                InputStream pluginXMLInputStream = URIConverter.INSTANCE.createInputStream(pluginXMLURI);
                IContributor contributor = ContributorFactorySimple.createContributor(bundleSymbolicName);
                registry.addContribution(pluginXMLInputStream, contributor, false, bundleSymbolicName, resourceBundle, null);
              }
            }
            catch (IOException exception)
            {
              INSTANCE.log(exception);
            }
          }
        }

        // Process the extension for the registry.
        //
        ExtensionProcessor.internalProcessExtensions();
      }
    }

    /**
     * Read all the registered extensions for Ecore's extension points.
     */
    private static void internalProcessExtensions()
    {
      new RegistryReader
        (RegistryFactory.getRegistry(),
         EcorePlugin.INSTANCE.getSymbolicName(),
         PACKAGE_REGISTRY_IMPLEMENTATION_PPID)
      {
        IConfigurationElement previous;

        @Override
        protected boolean readElement(IConfigurationElement element)
        {
          if (element.getName().equals("registry"))
          {
            String implementationClass = element.getAttribute("class");
            if (implementationClass == null)
            {
              logMissingAttribute(element, "class");
            }
            else
            {
              if (defaultRegistryImplementation != null)
              {
                if (previous != null)
                {
                  INSTANCE.log("Both '" + previous.getContributor().getName() + "' and '" + element.getContributor().getName() + "' register a package registry implementation");
                }
                if (defaultRegistryImplementation instanceof EPackageRegistryImpl.Delegator)
                {
                  return false;
                }
              }
              try
              {
                defaultRegistryImplementation = (EPackage.Registry)element.createExecutableExtension("class");
                previous = element;
              }
              catch (CoreException exception)
              {
                INSTANCE.log(exception);
              }
              return true;
            }
          }
          return false;
        }

      }.readRegistry();

      new GeneratedPackageRegistryReader(getEPackageNsURIToGenModelLocationMap(false)).readRegistry();
      new DynamicPackageRegistryReader().readRegistry();
      new FactoryOverrideRegistryReader().readRegistry();
      new ExtensionParserRegistryReader().readRegistry();
      new ProtocolParserRegistryReader().readRegistry();
      new ContentParserRegistryReader().readRegistry();
      new ContentHandlerRegistryReader().readRegistry();
      new URIMappingRegistryReader().readRegistry();
      new ValidationDelegateRegistryReader().readRegistry();
      new SettingDelegateFactoryRegistryReader().readRegistry();
      new InvocationDelegateFactoryRegistryReader().readRegistry();
      new QueryDelegateFactoryRegistryReader().readRegistry();
      new ConversionDelegateFactoryRegistryReader().readRegistry();
    }
  }

  /**
   * Determine all the available plugin.xml resources.
   */
  private static List<URI> getPluginXMLs(ClassLoader classLoader)
  {
    List<URI> result = new ArrayList<URI>();

    String classpath = null;
    try
    {
      // Try to get the classpath from the class loader.
      //
      Method method = classLoader.getClass().getMethod("getClassPath", (Class<?>) null);
      if (method != null)
      {
        classpath = (String) method.invoke(classLoader, (Object) null);
      }
    }
    catch (Throwable throwable)
    {
      // Failing thet, get it from the system properties.
      //
      classpath = System.getProperty("java.class.path");
    }

    // Keep track of whether we find any plugin.xml in the parent of a folder on the classpath, i.e., whether we're in development mode with bin folders on the classpath.
    //
    boolean nonClasspathXML = false;

    // If we have a classpath to use...
    //
    if (classpath != null)
    {
      // Split out the entries on the classpath.
      //
      for (String classpathEntry: classpath.split(";"))
      {
        classpathEntry = classpathEntry.trim();

        // Determine if the entry is a folder or an archive file.
        //
        File file = new File(classpathEntry);
        if (file.isDirectory())
        {
          // Determine if there is a plugin.xml at the root of the folder.
          //
          File pluginXML = new File(file, "plugin.xml");
          if (!pluginXML.exists())
          {
            // If not, check if there is one in the parent folder.
            //
            pluginXML = new File(file.getParentFile(), "plugin.xml");
            if (pluginXML.isFile())
            {
              // If there is, then we have plugin.xml files that aren't on the classpath.
              //
              nonClasspathXML = true;
            }
            else
            {
              // Otherwise this is bogus too.
              //
              pluginXML = null;
            }
          }

          // If we found a plugin.xml, create a URI for it.
          //
          if (pluginXML != null)
          {
            result.add(URI.createFileURI(pluginXML.getPath()));
          }
        }
        else if (file.isFile())
        {
          // The file must be a jar...
          //
          JarFile jarFile = null;
          try
          {
            // Look for a plugin.xml entry...
            //
            jarFile = new JarFile(classpathEntry);
            ZipEntry entry = jarFile.getEntry("plugin.xml");
            if (entry != null)
            {
              // If we find one, create a URI for it.
              //
              result.add(URI.createURI("archive:" + URI.createFileURI(classpathEntry) + "!/" + entry));
            }
          }
          catch (IOException exception)
          {
            // Ignore.
          }
          finally
          {
            if (jarFile != null)
            {
              try
              {
                jarFile.close();
              }
              catch (IOException exception)
              {
                INSTANCE.log(exception);
              }
            }
          }
        }
      }
    }

    // If we didn't find any non-classpath plugin.xml files, use the class loader to enumerate all the plugin.xml files.
    // This is more reliable given the possibility of specialized class loader behavior.
    //
    if (!nonClasspathXML)
    {
      result.clear();
      try
      {
        for (Enumeration<URL> resources = classLoader.getResources("plugin.xml"); resources.hasMoreElements(); )
        {
          // Create a URI for each plugin.xml found by the class loader.
          //
          URL url = resources.nextElement();
          result.add(URI.createURI(url.toURI().toString()));
        }
      }
      catch (IOException exception)
      {
        INSTANCE.log(exception);
      }
      catch (URISyntaxException exception)
      {
        INSTANCE.log(exception);
      }
    }

    return result;
  }

  @Override
  public String getSymbolicName()
  {
    ResourceLocator resourceLocator = getPluginResourceLocator();
    if (resourceLocator instanceof InternalEclipsePlugin)
    {
      return ((InternalEclipsePlugin)resourceLocator).getSymbolicName();
    }
    else
    {
      return "org.eclipse.emf.ecore";
    }
  }

  /**
   * The default registry implementation singleton.
   */
  private static EPackage.Registry defaultRegistryImplementation; 

  /**
   * Returns the default registry implementation singleton.
   * @return the default registry implementation singleton.
   */
  public static EPackage.Registry getDefaultRegistryImplementation()
  {
    return defaultRegistryImplementation;
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
    if (workspaceRoot == null && IS_RESOURCES_BUNDLE_AVAILABLE && System.getProperty("org.eclipse.emf.ecore.plugin.EcorePlugin.doNotLoadResourcesPlugin") == null)
    {
      workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
    }
    return workspaceRoot;
  }

  private static class PDEHelper
  {
    private static final Method PLUGIN_MODEL_BASE_GET_BUNDLE_DESCRIPTION_METHOD;
    private static final Method PLUGIN_MODEL_BASE_GET_UNDERLYING_RESOURCE_METHOD;
    private static final Method PLUGIN_MODEL_BASE_GET_INSTALL_LOCATION_METHOD;
    private static final Method PLUGIN_MODEL_BASE_GET_EXTENSIONS_METHOD;
    private static final Method PLUGIN_REGISTRY_GET_ACTIVE_MODELS_METHOD;
    private static final Method BUNDLE_DESCRIPTION_GET_SYMBOLIC_NAME_METHOD;
    private static final Method EXTENSIONS_GET_EXTENSIONS_METHOD;
    private static final Method PLUGIN_EXTENSION_GET_POINT_METHOD;
    private static final Method PLUGIN_EXTENSION_GET_CHILDREN_METHOD;
    private static final Class<?> PLUGIN_ELEMENT_CLASS;
    private static final Method PLUGIN_ELEMENT_GET_ATTRIBUTE_METHOD;
    private static final Method PLUGIN_ATTRIBUTE_GET_VALUE_METHOD;
    private static final boolean IS_PDE_BUNDLE_AVAILABLE;
  
    static
    {
      Method pluginModelBaseGetBundleDescriptionMethod = null;
      Method pluginModelBaseGetUnderlyingResourceMethod = null;
      Method pluginModelBaseGetInstallLocationMethod = null;
      Method pluginModelBaseGetExtensionsMethod = null;
      Method pluginRegistryGetActiveModelsMethod = null;
      Method bundleDescriptionGetSymbolicNameMethod = null;
      Method extensionsGetExtensionsMethod = null;
      Method pluginExtensionGetPointMethod = null;
      Method pluginExtensionGetChildrenMethod = null;
      Class<?> pluginElementClass = null;
      Method pluginElementGetAttributeMethod = null;
      Method pluginAttributeGetValueMethod = null;
      boolean isPDEBundleAvailable = false;
  
      try
      {
        Class<?> pluginModelBaseClass = CommonPlugin.loadClass("org.eclipse.pde.core", "org.eclipse.pde.core.plugin.IPluginModelBase");
        pluginModelBaseGetBundleDescriptionMethod = pluginModelBaseClass.getMethod("getBundleDescription");
        pluginModelBaseGetUnderlyingResourceMethod = pluginModelBaseClass.getMethod("getUnderlyingResource");
        pluginModelBaseGetInstallLocationMethod = pluginModelBaseClass.getMethod("getInstallLocation");
        pluginModelBaseGetExtensionsMethod = pluginModelBaseClass.getMethod("getExtensions");
        Class<?> pluginRegistryClass = CommonPlugin.loadClass("org.eclipse.pde.core", "org.eclipse.pde.core.plugin.PluginRegistry");
        pluginRegistryGetActiveModelsMethod = pluginRegistryClass.getMethod("getActiveModels", boolean.class);
        Class<?> bundleDescriptionClass = CommonPlugin.loadClass("org.eclipse.pde.core", "org.eclipse.osgi.service.resolver.BundleDescription");
        bundleDescriptionGetSymbolicNameMethod = bundleDescriptionClass.getMethod("getSymbolicName");
        Class<?> extensionsClass = CommonPlugin.loadClass("org.eclipse.pde.core", "org.eclipse.pde.core.plugin.IExtensions");
        extensionsGetExtensionsMethod = extensionsClass.getMethod("getExtensions");
        Class<?> pluginExtensionClass = CommonPlugin.loadClass("org.eclipse.pde.core", "org.eclipse.pde.core.plugin.IPluginExtension");
        pluginExtensionGetPointMethod = pluginExtensionClass.getMethod("getPoint");
        pluginExtensionGetChildrenMethod = pluginExtensionClass.getMethod("getChildren");
        pluginElementClass = CommonPlugin.loadClass("org.eclipse.pde.core", "org.eclipse.pde.core.plugin.IPluginElement");
        pluginElementGetAttributeMethod = pluginElementClass.getMethod("getAttribute", String.class);
        Class<?> pluginAttributeClass = CommonPlugin.loadClass("org.eclipse.pde.core", "org.eclipse.pde.core.plugin.IPluginAttribute");
        pluginAttributeGetValueMethod = pluginAttributeClass.getMethod("getValue");
        isPDEBundleAvailable = true;
      }
      catch (Throwable exception)
      {
        // Ignore.
      }
  
      PLUGIN_MODEL_BASE_GET_BUNDLE_DESCRIPTION_METHOD = pluginModelBaseGetBundleDescriptionMethod;
      PLUGIN_MODEL_BASE_GET_UNDERLYING_RESOURCE_METHOD = pluginModelBaseGetUnderlyingResourceMethod;
      PLUGIN_MODEL_BASE_GET_INSTALL_LOCATION_METHOD = pluginModelBaseGetInstallLocationMethod;
      PLUGIN_MODEL_BASE_GET_EXTENSIONS_METHOD = pluginModelBaseGetExtensionsMethod;
      PLUGIN_REGISTRY_GET_ACTIVE_MODELS_METHOD = pluginRegistryGetActiveModelsMethod;
      BUNDLE_DESCRIPTION_GET_SYMBOLIC_NAME_METHOD = bundleDescriptionGetSymbolicNameMethod;
      EXTENSIONS_GET_EXTENSIONS_METHOD = extensionsGetExtensionsMethod;
      PLUGIN_EXTENSION_GET_POINT_METHOD = pluginExtensionGetPointMethod;
      PLUGIN_EXTENSION_GET_CHILDREN_METHOD = pluginExtensionGetChildrenMethod;
      PLUGIN_ELEMENT_CLASS = pluginElementClass;
      PLUGIN_ELEMENT_GET_ATTRIBUTE_METHOD = pluginElementGetAttributeMethod;
      PLUGIN_ATTRIBUTE_GET_VALUE_METHOD = pluginAttributeGetValueMethod;
      IS_PDE_BUNDLE_AVAILABLE = isPDEBundleAvailable;
    }
  
    private static Object invoke(Object object, Method method, Object... arguments)
    {
      try
      {
        return method.invoke(object, arguments);
      }
      catch (Exception exception)
      {
        return null;
      }
    }
  
    private static void computeModels(Map<URI, URI> pluginMap, Map<String, URI> nsURIMap)
    {
      // Cache the workspace for use in the loop.
      //
      IWorkspaceRoot root = getWorkspaceRoot();
  
      // Iterate over all the active models in the workspace and target platform.
      //
      // IPluginModelBase[] activeModels = PluginRegistry.getActiveModels(false);
      //
      Object[] activeModels = (Object[])invoke(null, PLUGIN_REGISTRY_GET_ACTIVE_MODELS_METHOD, Boolean.FALSE);
      LOOP:
      for (Object activeModel : activeModels)
      {
        // Determine the symbolic name, underlying resource, if any, and the install location.
        //
        // BundleDescription bundleDescription = activeModel.getBundleDescription();
        // String symbolicName = bundleDescription.getSymbolicName();
        // IResource underlyingResource = activeModel.getUnderlyingResource();
        // String installLocation = activeModel.getInstallLocation();
        //
        Object bundleDescription = invoke(activeModel, PLUGIN_MODEL_BASE_GET_BUNDLE_DESCRIPTION_METHOD);
        String symbolicName = (String)invoke(bundleDescription, BUNDLE_DESCRIPTION_GET_SYMBOLIC_NAME_METHOD);
        IResource underlyingResource = (IResource)invoke(activeModel, PLUGIN_MODEL_BASE_GET_UNDERLYING_RESOURCE_METHOD);
        String installLocation = (String)invoke(activeModel, PLUGIN_MODEL_BASE_GET_INSTALL_LOCATION_METHOD);
  
        // The URI for the location is determined from the underlying resource or the install location, with preference to the former if available.
        //
        URI location = null;
        if (underlyingResource != null)
        {
          // If there is an underlying resource, use the platform resource URI referencing the project in the workspace as the location.
          //
          location = URI.createPlatformResourceURI(underlyingResource.getProject().getFullPath().toString(), true);
        }
        else if (installLocation != null)
        {
          // Otherwise, the install location in the file system is used...
          //
          File file = new File(installLocation);
          if (file.isDirectory())
          {
            // If the file is a directory, create a file URI for that directory.
            //
            location = URI.createFileURI(installLocation);
          }
          else
          {
            // Otherwise, the location must be an archive, create an archive URI for the file URI of the jar.
            //
            location = URI.createURI("archive:" + URI.createFileURI(installLocation) + "!/");
          }
        }
  
        // If we're able to compute a location...
        //
        if (location != null)
        {
          // The logical URI will be computed when we need it to deal with generated package extension points.
          //
          URI logicalLocation = null;
  
          // Iterate over the plugin's extensions...
          //
          // IExtensions extensions = activeModel.getExtensions();
          // IPluginExtension[] pluginExtensions = extensions.getExtensions();
          //
          Object extensions = invoke(activeModel, PLUGIN_MODEL_BASE_GET_EXTENSIONS_METHOD);
          Object[] pluginExtensions = (Object[])invoke(extensions, EXTENSIONS_GET_EXTENSIONS_METHOD);
          for (Object pluginExtension : pluginExtensions)
          {
            // Consider only the generated package extension points.
            //
            // String point = pluginExtension.getPoint();
            //
            String point = (String)invoke(pluginExtension, PLUGIN_EXTENSION_GET_POINT_METHOD);
            if ("org.eclipse.emf.ecore.generated_package".equals(point))
            {
              // Iterate over the child elements, i.e., the <package> elements, of the generated package extension point...
              //
              // IPluginObject[] children = pluginExtension.getChildren();
              //
              Object[] children = (Object[])invoke(pluginExtension, PLUGIN_EXTENSION_GET_CHILDREN_METHOD);
              for (Object child : children)
              {
                // if (child instanceof IPluginElement)
                //
                if (PLUGIN_ELEMENT_CLASS.isInstance(child))
                {
                  // If the the uri and the genModel attributes are present...
                  //
                  // IPluginElement pluginElement = (IPluginElement)child;
                  // IPluginAttribute uri = pluginElement.getAttribute("uri");
                  // IPluginAttribute genModel = pluginElement.getAttribute("genModel");
                  //
                  Object uri = invoke(child, PLUGIN_ELEMENT_GET_ATTRIBUTE_METHOD, "uri");
                  Object genModel = invoke(child, PLUGIN_ELEMENT_GET_ATTRIBUTE_METHOD, "genModel");
                  if (uri != null && genModel != null)
                  {
                    // We need the logical location of the plugin, so if we haven't computed it already, do so now..
                    // This creates folder mappings as a side-effect.
                    //
                    if (logicalLocation == null)
                    {
                      // We'll always want to redirect the platform plugin URI to the platform resource URI for this plugin...
                      //
                      URI platformPluginURI = URI.createPlatformPluginURI(symbolicName, true).appendSegment("");
                      if (location.isPlatformResource())
                      {
                        // If we're computing the plugin map and the physical location is in the workspace, map the platform plugin URI to the platform resource URI of the workspace project.
                        //
                        if (pluginMap != null)
                        {
                          pluginMap.put(platformPluginURI, location.appendSegment(""));
                        }
  
                        // The physical location is also the logical location.
                        //
                        logicalLocation = location;
                      }
                      else
                      {
                        // The logical location will be a platform resource URI as if the external plugin were in the workspace.
                        //
                        logicalLocation = URI.createPlatformResourceURI(symbolicName, true);
  
                        // We'll create a folder mapping for this logical location...
                        //
                        URI resourceURI = logicalLocation.appendSegment("");
  
                        // But only an actual project doesn't already exist in the workspace.
                        //
                        boolean exists = root.getProject(symbolicName).isAccessible();
  
                        // If we're computing the plugin map...
                        //
                        if (pluginMap != null)
                        {
                          // If the physical location is an external folder...
                          //
                          if (location.isFile())
                          {
                            // If the physical location is an external folder, map the platform plugin URI to file URI of that external folder.
                            //
                            pluginMap.put(platformPluginURI, location.appendSegment(""));
                            if (!exists)
                            {
                              // If there is no corresponding project physically present in the workspace, also map the platform resource URI of the plugin to the file URI of the external folder.
                              //
                              pluginMap.put(resourceURI, location.appendSegment(""));
                            }
                          }
                          else
                          {
                            // If the physical location is an external jar, map the platform plugin URI to the archive URI of that external jar.
                            //
                            pluginMap.put(platformPluginURI, location);
                            if (!exists)
                            {
                              // If there is no corresponding project physically present in the workspace, also map the platform resource URI of the plugin to the archive URI of that external jar.
                              //
                              pluginMap.put(resourceURI, location);
                            }
                          }
                        }
                      }
                    }
  
                    // If we're not computing the nsURI map, we're done with this plugin.
                    //
                    if (nsURIMap == null)
                    {
                      continue LOOP;
                    }
  
                    // Map the nsURI to the logical location URI of the registered GenModel.
                    //
                    // nsURIMap.put(uri.getValue(), logicalLocation.appendSegments(new Path(genModel.getValue()).segments()));
                    //
                    nsURIMap.put((String)invoke(uri, PLUGIN_ATTRIBUTE_GET_VALUE_METHOD), logicalLocation.appendSegments(new Path((String)invoke(genModel, PLUGIN_ATTRIBUTE_GET_VALUE_METHOD)).segments()));
                  }
                }
              }
            }
          }
        }
      }
    }
  }

  public static final String DYNAMIC_PACKAGE_PPID = "dynamic_package";
  public static final String GENERATED_PACKAGE_PPID = "generated_package";
  public static final String FACTORY_OVERRIDE_PPID = "factory_override";
  public static final String EXTENSION_PARSER_PPID = "extension_parser";
  public static final String PROTOCOL_PARSER_PPID = "protocol_parser";
  public static final String CONTENT_PARSER_PPID = "content_parser";
  public static final String CONTENT_HANDLER_PPID = "content_handler";
  public static final String SCHEME_PARSER_PPID = "scheme_parser";
  public static final String URI_MAPPING_PPID = "uri_mapping";
  public static final String PACKAGE_REGISTRY_IMPLEMENTATION_PPID = "package_registry_implementation";
  public static final String VALIDATION_DELEGATE_PPID = "validation_delegate";
  public static final String SETTING_DELEGATE_PPID = "setting_delegate";
  public static final String INVOCATION_DELEGATE_PPID = "invocation_delegate";
  public static final String QUERY_DELEGATE_PPID = "query_delegate";
  public static final String CONVERSION_DELEGATE_PPID = "conversion_delegate";
}