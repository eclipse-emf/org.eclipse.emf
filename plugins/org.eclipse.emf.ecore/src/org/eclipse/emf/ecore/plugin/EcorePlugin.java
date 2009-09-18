/**
 * <copyright>
 *
 * Copyright (c) 2002-2009 IBM Corporation and others.
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
 * $Id: EcorePlugin.java,v 1.24 2009/09/18 18:10:41 khussey Exp $
 */
package org.eclipse.emf.ecore.plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.osgi.framework.BundleContext;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EPackageRegistryImpl;


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
   * @return a map from package namespace to GenModel location.
   */
  public static Map<String, URI> getEPackageNsURIToGenModelLocationMap()
  {
    if (ePackageNsURIToGenModelLocationMap == null)
    {
      ePackageNsURIToGenModelLocationMap = new HashMap<String, URI>();
    }
    return ePackageNsURIToGenModelLocationMap;
  }

  /**
   * Computes a map from <code>platform:/resource/&lt;plugin-location>/</code> {@link URI} to 
   * <code>platform:/plugin/&lt;plugin-id>/</code> URI
   * for each URI in the collection of the form <code>platform:/plugin/&lt;plugin-id>/...</code>.
   * This allows each plugin to be {@link org.eclipse.emf.ecore.resource.URIConverter#getURIMap() treated} 
   * as if it were a project in the workspace.
   * If the workspace already contains a project for the plugin location, no mapping is produced.
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
    return result;
  }
  
  private static Pattern bundleSymbolNamePattern;
  private static byte [] NO_BYTES = new byte [0];
  
  /**
   * Computes a map from <code>platform:/plugin/&lt;plugin-id>/</code> {@link URI} to 
   * <code>platform:/resource/&lt;plugin-location>/</code> URI
   * for each plugin project in the workspace.
   * This allows each plugin from the runtime to be {@link org.eclipse.emf.ecore.resource.URIConverter#getURIMap() redirected} 
   * to its active version in the workspace.
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
                inputStream = manifest.getContents(); 
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
                  parser.parse(new InputSource(plugin.getContents()), handler);
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
    
    return result;
  }
  
  /**
   * Computes a map so that plugins in the workspace will override those in the environment
   * and so that plugins with Ecore and GenModels will look like projects in the workspace.
   * It's implemented like this:
   *<pre>
   *  Map result = new HashMap();
   *  result.putAll(computePlatformPluginToPlatformResourceMap());
   *  result.putAll(computePlatformResourceToPlatformPluginMap(new HashSet(EcorePlugin.getEPackageNsURIToGenModelLocationMap().values())));
   *  return result;
   *</pre>
   * @return computes a map so that plugins in the workspace will override those in the environment
   * and so that plugins with Ecore and GenModels will look like projects in the workspace.
   * @see org.eclipse.emf.ecore.resource.URIConverter#getURIMap()
   * @see URI
   * @see #computePlatformPluginToPlatformResourceMap()
   * @see #computePlatformResourceToPlatformPluginMap(Collection)
   */
  public static Map<URI, URI> computePlatformURIMap()
  {
    Map<URI, URI> result = new HashMap<URI, URI>();
    result.putAll(computePlatformPluginToPlatformResourceMap());
    result.putAll(computePlatformResourceToPlatformPluginMap(new HashSet<URI>(EcorePlugin.getEPackageNsURIToGenModelLocationMap().values())));
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

      if (IS_RESOURCES_BUNDLE_AVAILABLE && System.getProperty("org.eclipse.emf.ecore.plugin.EcorePlugin.doNotLoadResourcesPlugin") == null)
      {
        workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
      }

      new RegistryReader
        (Platform.getExtensionRegistry(),
         EcorePlugin.getPlugin().getBundle().getSymbolicName(), 
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
                  log("Both '" + previous.getContributor().getName() + "' and '" + element.getContributor().getName() + "' register a package registry implementation");
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
                log(exception);
              }
              return true;
            }
          }
          return false;
        }
        
      }.readRegistry();

      new GeneratedPackageRegistryReader(getEPackageNsURIToGenModelLocationMap()).readRegistry();
      new DynamicPackageRegistryReader().readRegistry();
      new FactoryOverrideRegistryReader().readRegistry();
      new ExtensionParserRegistryReader().readRegistry();
      new ProtocolParserRegistryReader().readRegistry();
      new ContentParserRegistryReader().readRegistry();
      new ContentHandlerRegistryReader().readRegistry();
      new URIMappingRegistryReader().readRegistry();
      new ValidationDelegateRegistryReader().readRegistry();
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
    return workspaceRoot;
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
}