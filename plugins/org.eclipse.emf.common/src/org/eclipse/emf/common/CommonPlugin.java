/**
 * Copyright (c) 2002-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.common;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.osgi.service.resolver.BundleDescription;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.resource.Capability;


/**
 * The <b>Plugin</b> for the model EMF.Common library.
 * EMF must run 
 * within an Eclipse workbench,
 * within a headless Eclipse workspace,
 * or just stand-alone as part of some other application.
 * To support this, all resource access should be directed to the resource locator,
 * which can redirect the service as appropriate to the runtime.
 * During stand-alone invocation no plugin initialization takes place.
 * In this case, common.resources.jar must be on the CLASSPATH.
 * @see #INSTANCE
 */
public final class CommonPlugin extends EMFPlugin 
{
  /**
   * The singleton instance of the plugin.
   */
  public static final CommonPlugin INSTANCE = new CommonPlugin();

  /**
   * The one instance of this class.
   */
  private static Implementation plugin;

  /**
   * Creates the singleton instance.
   */
  private CommonPlugin()
  {
    super(new ResourceLocator[] {});
  }

  @Override
  public ResourceLocator getPluginResourceLocator()
  {
    return plugin;
  }

  /**
   * Returns the singleton instance of the Eclipse plugin.
   * @return the singleton instance.
   */
  public static Implementation getPlugin()
  {
    return plugin;
  }

  /**
   * Use the platform, if available, to convert to a local URI.
   */
  public static URI asLocalURI(URI uri)
  {
    return plugin == null ? uri : Implementation.asLocalURI(uri);
  }

  /**
   * Use the platform, if available, to resolve the URI.
   */
  public static URI resolve(URI uri)
  {
    return plugin == null ? uri : Implementation.resolve(uri);
  }

  /**
   * Use the platform, if available, to load the named class using the right class loader.
   */
  public static Class<?> loadClass(String pluginID, String className) throws ClassNotFoundException
  {
    return plugin == null ? Class.forName(className) : Implementation.loadClass(pluginID, className);
  }

  /**
   * Computes a list of {@link ElementRecord element records} for each requested extension point.
   * Each key in the map is extension point ID. 
   * <p>
   * The corresponding value is a list of the extension point element records associated with that extension point.
   * </p>
   * <p>
   * Each root element record has synthetic attributes derived from the extension point:
   * </p>
   * <dl style="text-index: 1em;">
   *   <dt>{@code point}</dt>
   *   <dd>The extension point ID itself.</dd>
   *   <dt>{@code symbolicName}</dt>
   *   <dd>The symbolic name of the bundle that contains the extension point.</dd>
   *   <dt>{@code location}</dt>
   *   <dd>
   *   The root location URI the bundle containing the extension point.
   *   For example, {@code platform:/resource/org.example.plugin} for an extension point in the workspace, 
   *   {@code file:/folder/org.eclipse.plugin} for folder bundle in the target platform, 
   *   or {@code archive:file:/file/folder/org.example.plugin.jar!/} for a jarred bundle in the target platform.
   *   This is useful for resolving a relative path in attribute value to its absolute path in the bundle.
   *   </dd>
   * </dl>
   * <p>
   * This method uses the Plug-in Development Environment (PDE) to compute the results.
   * It will include results for plug-ins in the workspace as well as for plug-ins in the target platform.
   * If PDE is not available, this information cannot be computed.
   * </p>
   * 
   * @param extensionPoints a set of extension points to query; if it's {@code null} or empty, all extension points will be queried.
   * @return a map of extension point data, or {@code null} if PDE is not available.
   * @since 2.14
   */
  public static Map<String, List<ElementRecord>> getTargetPlatformExtensionPoints(Set<String> extensionPoints)
  {
    if (IS_ECLIPSE_RUNNING && PDEHelper.IS_PDE_BUNDLE_AVAILABLE)
    {
      return PDEHelper.computeModels(extensionPoints);
    }
    else
    {
      return null;
    }
  }

  /**
   * Computes a map from bundle symbolic name to the bundle's location URI.
   * <p>
   * This method uses the Plug-in Development Environment (PDE) to compute the results.
   * It will include results for plug-ins in the workspace as well as for plug-ins in the target platform.
   * If PDE is not available, this information cannot be computed.
   * </p>
   * 
   * @return a map from bundle symbolic name to the bundle's location URI, or {@code null} if PDE is not available.
   * @since 2.14
   */
  public static Map<String, URI> getTargetPlatformBundleMappings()
  {
    if (IS_ECLIPSE_RUNNING && PDEHelper.IS_PDE_BUNDLE_AVAILABLE)
    {
      return PDEHelper.computeTargetPlatformBundleMappings();
    }
    else
    {
      return null;
    }
  }

  private static volatile Method collatorGetInstanceMethod;
  private static volatile boolean collatorGetInstanceMethodInitialized;
  private static Method getCollatorGetInstanceMethod()
  {
    if (!collatorGetInstanceMethodInitialized)
    {
      try
      {
        Class<?> collatorClass = loadClass("com.ibm.icu", "com.ibm.icu.text.Collator");
        collatorGetInstanceMethod = collatorClass.getMethod("getInstance", Locale.class);
      }
      catch (Throwable throwable)
      {
        // Assume the class is not available.
      }
      collatorGetInstanceMethodInitialized = true;
    }
    return collatorGetInstanceMethod;
  }

  /**
   * Returns a string comparator appropriate for collating strings for the {@link Locale#getDefault() current locale}.
   * @return a string comparator appropriate for collating strings for the {@link Locale#getDefault() current locale}.
   */
  public Comparator<String> getComparator()
  {
    return getComparator(Locale.getDefault());
  }

  /**
   * Returns a string comparator appropriate for collating strings for the give locale.
   * This will use ICU, when available that plug-in is available, or {@link Collator} otherwise.
   * @param locale the locale for which a comparator is needed.
   * @return a string comparator appropriate for collating strings for the give locale.
   */
  @SuppressWarnings("unchecked")
  public Comparator<String> getComparator(Locale locale)
  {
    Method collatorGetInstanceMethod = getCollatorGetInstanceMethod();
    if (collatorGetInstanceMethod != null)
    {
      try
      {
        return (Comparator<String>)collatorGetInstanceMethod.invoke(null, locale);
      }
      catch (Throwable eception)
      {
        // Just return the default.
      }
    }
    return (Comparator<String>)(Comparator<?>)Collator.getInstance(locale);
  }

  /**
   * The actual implementation of the Eclipse <b>Plugin</b>.
   */
  public static class Implementation extends EclipsePlugin 
  {
    /**
     * Creates an instance.
     */
    public Implementation()
    {
      super();

      // Remember the static instance.
      //
      plugin = this;
    }

    /**
     * Use the platform to convert to a local URI.
     */
    protected static URI asLocalURI(URI uri)
    {
      try
      {
        String fragment = uri.fragment();
        URL url = FileLocator.toFileURL(new URL(uri.trimFragment().toString()));
        return fix(url, fragment);
      }
      catch (IOException exception)
      {
        // Ignore the exception and return the original URI.
      }
      return uri;
    }

    /**
     * Use the platform to convert to a local URI.
     */
    protected static URI resolve(URI uri)
    {
      String fragment = uri.fragment();
      URI uriWithoutFragment = uri.trimFragment();
      String uriWithoutFragmentToString = uriWithoutFragment.toString();
      
      URL url = null;
      try
      {
        url = FileLocator.resolve(new URL(uriWithoutFragmentToString));
      }
      catch (IOException exception1)
      {
        // Platform.resolve() doesn't work if the project is encoded.
        //
        try
        {
          uriWithoutFragmentToString = URI.decode(uriWithoutFragmentToString);
          url = FileLocator.resolve(new URL(uriWithoutFragmentToString));
        }
        catch (IOException exception2)
        {
          // Continue with the unresolved URI.
        }
      }
      if (url != null)
      {
        try
        {
          return fix(url, fragment);
        }
        catch (IOException exception)
        {
          // Return the original URI.
        }
      }
      
      return uri;
    }

    protected static URI fix(URL url, String fragment) throws IOException
    {
      // Only file-scheme URIs will be re-encoded. If a URI was decoded in the workaround
      // above, and Platform.resolve() didn't return a file-scheme URI, then this will return
      // an decoded URI.
      //
      URI result = 
        "file".equalsIgnoreCase(url.getProtocol()) ?
          URI.createFileURI(URI.decode(url.getFile())) :
          URI.createURI(url.toString());
      if (fragment != null)
      {
        result = result.appendFragment(fragment);
      }
      return result;
    }
    
    /**
     * Use the platform to load the named class using the right class loader.
     */
    public static Class<?> loadClass(String pluginID, String className) throws ClassNotFoundException
    {
      Bundle bundle = Platform.getBundle(pluginID);
      if (bundle == null)
      {
        throw new ClassNotFoundException(className + " cannot be loaded because because bundle " + pluginID + " cannot be resolved");
      }
      else
      {
        return bundle.loadClass(className);
      }
    }

    /**
     * @since 2.10
     */
    public static class Activator extends EMFPlugin.OSGiDelegatingBundleActivator
    {
      @Override
      protected BundleActivator createBundle()
      {
        return new Implementation();
      }
    }
  }

  /**
   * A specialized {@link HashMap} map that supports {@link #getTargetPlatformValues(String,String) computing} information from the target platform, if the PDE is available.
   * It is abstract because the {@link #createKey(String)} method must be specialized to convert each attribute's string value to a value of the map's key type.
   * 
   * @param <K> the type of the key.
   * @param <V> the type of the value.
   *
   * @since 2.14
   */
  public static abstract class SimpleTargetPlatformRegistryImpl<K, V> extends HashMap<K, V>
  {
    private static final long serialVersionUID = 1L;

    /**
     * Creates an instance.
     */
    public SimpleTargetPlatformRegistryImpl()
    {
    }

    /**
     * Returns the set of values computed by {@link CommonPlugin#getTargetPlatformExtensionPoints(Set) fetching} all the given extension points 
     * and {@link ElementRecord#getAttributes() looking up} the given attribute name in each {@link ElementRecord#getChildren() element} of that extension point.
     * Each attribute value is {@link #createKey(String) converted} to a value of the map's key type.
     * @param extensionPoint the qualified extension point name.
     * @param attributeName the attribute name to query.
     * @return the values computed from the target platform, if the Plug-in Development Environment is available, or a copy of the {@link #keySet()} otherwise.
     * @see #createKey(String)
     */
    protected Set<K> getTargetPlatformValues(String extensionPoint, String attributeName)
    {
      Map<String, List<CommonPlugin.ElementRecord>> targetPlatformExtensionPoints = getTargetPlatformExtensionPoints(Collections.singleton(extensionPoint));
      if (targetPlatformExtensionPoints != null)
      {
        Set<K> result = new LinkedHashSet<K>();
        List<CommonPlugin.ElementRecord> extensionPointElementRecords = targetPlatformExtensionPoints.get(extensionPoint);
        if (extensionPointElementRecords != null)
        {
          for (CommonPlugin.ElementRecord extensionPointRecord : extensionPointElementRecords)
          {
            for (ElementRecord elementRecord : extensionPointRecord.getChildren())
            {
              String attribute = elementRecord.getAttributes().get(attributeName);
              if (attribute != null)
              {
                result.add(createKey(attribute));
              }
            }
          }
        }
        return result;
      }
      else
      {
        return new LinkedHashSet<K>(keySet());
      }
    }

    /**
     * Returns the attribute value converted to a value of the key type.
     * @param attribute the attribute value.
     * @return the attribute value converted to a value of the key type.
     */
    protected abstract K createKey(String attribute);
  }

  /**
   * A simple representation of an element in a {@code plugin.xml}.
   * It has the obvious things you'd expect of an element, i.e., a name, attributes, and children.
   *
   * @since 2.14
   */
  public static final class ElementRecord
  {
    /**
     * The element name.
     */
    private final String name;

    /**
     * The attributes of the element.
     */
    private final Map<String, String> attributes = new TreeMap<String, String>();

    /**
     * The children of the element.
     */
    private final List<ElementRecord> children = new ArrayList<ElementRecord>();

    /**
     * Creates an element with the given name.
     * 
     * @param name the name of the element.
     */
    private ElementRecord(String name)
    {
      this.name = name;
    }

    /**
     * Returns the name of the element.
     * @return the name of the element.
     */
    public String getName()
    {
      return name;
    }

    /**
     * Returns the attributes of the element.
     * @return the attributes of the element.
     */
    public Map<String, String> getAttributes()
    {
      return Collections.unmodifiableMap(attributes);
    }

    /**
     * Returns the children elements.
     * @return the children elements.
     */
    public List<ElementRecord> getChildren()
    {
      return Collections.unmodifiableList(children);
    }

    /**
     * A helpful override for debugging.
     */
    @Override
    public String toString()
    {
      return "" + name + " attributes= " + attributes + " children=" + children;
    }
  }

  private static class PDEHelper
  {
    private static final String ECORE_GENERATED_PACKAGE = "org.eclipse.emf.ecore.generated_package";

    private static final String ECORE_DYNAMIC_PACKAGE = "org.eclipse.emf.ecore.dynamic_package";

    private static final Method PLUGIN_MODEL_BASE_GET_BUNDLE_DESCRIPTION_METHOD;

    private static final Method PLUGIN_MODEL_BASE_GET_UNDERLYING_RESOURCE_METHOD;

    private static final Method RESOURCE_GET_PROJECT_METHOD;

    private static final Method RESOURCE_GET_FULL_PATH_METHOD;

    private static final Method PLUGIN_MODEL_BASE_GET_INSTALL_LOCATION_METHOD;

    private static final Method PLUGIN_MODEL_BASE_GET_EXTENSIONS_METHOD;

    private static final Method PLUGIN_REGISTRY_GET_ACTIVE_MODELS_METHOD;

    private static final Method EXTENSIONS_GET_EXTENSIONS_METHOD;

    private static final Method PLUGIN_EXTENSION_GET_POINT_METHOD;

    private static final Method PLUGIN_EXTENSION_GET_CHILDREN_METHOD;

    private static final Class<?> PLUGIN_ELEMENT_CLASS;

    private static final Method PLUGIN_ELEMENT_GET_ATTRIBUTES_METHOD;

    private static final Method PLUGIN_OBJECT_GET_NAME_METHOD;

    private static final Method PLUGIN_ATTRIBUTE_GET_VALUE_METHOD;

    private static final boolean IS_PDE_BUNDLE_AVAILABLE;

    static
    {
      Method pluginModelBaseGetBundleDescriptionMethod = null;
      Method pluginModelBaseGetUnderlyingResourceMethod = null;
      Method resourceGetProjectMethod = null;
      Method resourceGetFullPathtMethod = null;
      Method pluginModelBaseGetInstallLocationMethod = null;
      Method pluginModelBaseGetExtensionsMethod = null;
      Method pluginRegistryGetActiveModelsMethod = null;
      Method extensionsGetExtensionsMethod = null;
      Method pluginExtensionGetPointMethod = null;
      Method pluginExtensionGetChildrenMethod = null;
      Class<?> pluginElementClass = null;
      Method pluginElementGetAttributesMethod = null;
      Method pluginObjectGetNameMethod = null;
      Method pluginAttributeGetValueMethod = null;
      boolean isPDEBundleAvailable = false;

      try
      {
        Class<?> pluginModelBaseClass = CommonPlugin.loadClass("org.eclipse.pde.core", "org.eclipse.pde.core.plugin.IPluginModelBase");
        pluginModelBaseGetBundleDescriptionMethod = pluginModelBaseClass.getMethod("getBundleDescription");
        pluginModelBaseGetUnderlyingResourceMethod = pluginModelBaseClass.getMethod("getUnderlyingResource");
        resourceGetProjectMethod = pluginModelBaseGetUnderlyingResourceMethod.getReturnType().getMethod("getProject");
        resourceGetFullPathtMethod = resourceGetProjectMethod.getReturnType().getMethod("getFullPath");
        pluginModelBaseGetInstallLocationMethod = pluginModelBaseClass.getMethod("getInstallLocation");
        pluginModelBaseGetExtensionsMethod = pluginModelBaseClass.getMethod("getExtensions");
        Class<?> pluginRegistryClass = CommonPlugin.loadClass("org.eclipse.pde.core", "org.eclipse.pde.core.plugin.PluginRegistry");
        pluginRegistryGetActiveModelsMethod = pluginRegistryClass.getMethod("getActiveModels", boolean.class);
        Class<?> extensionsClass = CommonPlugin.loadClass("org.eclipse.pde.core", "org.eclipse.pde.core.plugin.IExtensions");
        extensionsGetExtensionsMethod = extensionsClass.getMethod("getExtensions");
        Class<?> pluginExtensionClass = CommonPlugin.loadClass("org.eclipse.pde.core", "org.eclipse.pde.core.plugin.IPluginExtension");
        pluginExtensionGetPointMethod = pluginExtensionClass.getMethod("getPoint");
        pluginExtensionGetChildrenMethod = pluginExtensionClass.getMethod("getChildren");
        pluginElementClass = CommonPlugin.loadClass("org.eclipse.pde.core", "org.eclipse.pde.core.plugin.IPluginElement");
        pluginObjectGetNameMethod = pluginElementClass.getMethod("getName");
        pluginElementGetAttributesMethod = pluginElementClass.getMethod("getAttributes");
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
      RESOURCE_GET_PROJECT_METHOD = resourceGetProjectMethod;
      RESOURCE_GET_FULL_PATH_METHOD = resourceGetFullPathtMethod;
      PLUGIN_MODEL_BASE_GET_INSTALL_LOCATION_METHOD = pluginModelBaseGetInstallLocationMethod;
      PLUGIN_MODEL_BASE_GET_EXTENSIONS_METHOD = pluginModelBaseGetExtensionsMethod;
      PLUGIN_REGISTRY_GET_ACTIVE_MODELS_METHOD = pluginRegistryGetActiveModelsMethod;
      EXTENSIONS_GET_EXTENSIONS_METHOD = extensionsGetExtensionsMethod;
      PLUGIN_EXTENSION_GET_POINT_METHOD = pluginExtensionGetPointMethod;
      PLUGIN_EXTENSION_GET_CHILDREN_METHOD = pluginExtensionGetChildrenMethod;
      PLUGIN_ELEMENT_CLASS = pluginElementClass;
      PLUGIN_OBJECT_GET_NAME_METHOD = pluginObjectGetNameMethod;
      PLUGIN_ELEMENT_GET_ATTRIBUTES_METHOD = pluginElementGetAttributesMethod;
      PLUGIN_ATTRIBUTE_GET_VALUE_METHOD = pluginAttributeGetValueMethod;
      IS_PDE_BUNDLE_AVAILABLE = isPDEBundleAvailable && !"true".equals(System.getProperty("org.eclipse.emf.common.CommonPlugin.doNotUsePDE"));
    }

    @SuppressWarnings("unchecked")
    private static <T> T invoke(Object object, Method method, Object... arguments)
    {
      try
      {
        return (T)method.invoke(object, arguments);
      }
      catch (Exception exception)
      {
        return null;
      }
    }

    private static Map<String, URI> computeTargetPlatformBundleMappings()
    {
      final Map<String, URI> result = new TreeMap<String, URI>();
      new ActiveModelVisitor()
        {
          @Override
          void visitActiveModel(Object activeModel, BundleDescription bundleDescription, URI location)
          {
            String symbolicName = bundleDescription.getSymbolicName();
            if (symbolicName != null && location != null)
            {
              result.put(symbolicName, location);
            }
          }
        };
      return result;
    }

    private static Map<String, List<ElementRecord>> computeModels(final Set<String> extensionPoints)
    {
      final Map<String, List<ElementRecord>> result = new TreeMap<String, List<ElementRecord>>();
      new ActiveModelVisitor()
        {
          @Override
          void visitActiveModel(Object activeModel, BundleDescription bundleDescription, URI location)
          {
            String symbolicName = bundleDescription.getSymbolicName();

            // Iterate over the plugin's extensions...
            //
            // IExtensions extensions = activeModel.getExtensions();
            // IPluginExtension[] pluginExtensions = extensions.getExtensions();
            //
            Object extensions = invoke(activeModel, PLUGIN_MODEL_BASE_GET_EXTENSIONS_METHOD);
            Object[] pluginExtensions = invoke(extensions, EXTENSIONS_GET_EXTENSIONS_METHOD);
            if (pluginExtensions.length == 0)
            {
              if (extensionPoints != null)
              {
                if (extensionPoints.contains(ECORE_GENERATED_PACKAGE))
                {
                  List<Capability> capabilities = bundleDescription.getCapabilities(ECORE_GENERATED_PACKAGE);
                  if (!capabilities.isEmpty())
                  {
                    ElementRecord extensionRecord = new ElementRecord("extension");
                    recordExtensionAttributes(extensionRecord, ECORE_GENERATED_PACKAGE, symbolicName, location);

                    for (Capability capability : capabilities)
                    {
                      ElementRecord elementRecord = new ElementRecord("package");
                      Map<String, Object> attributes = capability.getAttributes();
                      elementRecord.attributes.put("uri", "" + attributes.get("uri"));
                      elementRecord.attributes.put("class", "" + attributes.get("class"));
                      elementRecord.attributes.put("genModel", "" + attributes.get("genModel"));

                      extensionRecord.children.add(elementRecord);
                    }

                    List<ElementRecord> elementRecords = getElementRecords(ECORE_GENERATED_PACKAGE);
                    elementRecords.add(extensionRecord);
                  }
                }
                if (extensionPoints.contains(ECORE_DYNAMIC_PACKAGE))
                {
                  List<Capability> capabilities = bundleDescription.getCapabilities(ECORE_DYNAMIC_PACKAGE);
                  if (!capabilities.isEmpty())
                  {
                    ElementRecord extensionRecord = new ElementRecord("extension");
                    recordExtensionAttributes(extensionRecord, ECORE_DYNAMIC_PACKAGE, symbolicName, location);

                    for (Capability capability : capabilities)
                    {
                      ElementRecord elementRecord = new ElementRecord("resource");
                      Map<String, Object> attributes = capability.getAttributes();
                      elementRecord.attributes.put("uri", "" + attributes.get("uri"));
                      elementRecord.attributes.put("location", "" + attributes.get("location"));

                      extensionRecord.children.add(elementRecord);
                    }

                    List<ElementRecord> elementRecords = getElementRecords(ECORE_DYNAMIC_PACKAGE);
                    elementRecords.add(extensionRecord);
                  }
                }
              }
            }
            else
            {
              for (Object pluginExtension : pluginExtensions)
              {
                // String point = pluginExtension.getPoint();
                //
                String point = invoke(pluginExtension, PLUGIN_EXTENSION_GET_POINT_METHOD);

                // Process all or the specified extension points.
                //
                if (extensionPoints == null || extensionPoints.isEmpty() || extensionPoints.contains(point))
                {
                  ElementRecord elementRecord = visitElement(pluginExtension);
                  recordExtensionAttributes(elementRecord, point, symbolicName, location);
                  List<ElementRecord> elementRecords = getElementRecords(point);
                  elementRecords.add(elementRecord);
                }
              }
            }
          }

          private void recordExtensionAttributes(ElementRecord elementRecord, String point, String symbolicName, URI location)
          {
            if (location != null)
            {
              elementRecord.attributes.put("point", point);
              elementRecord.attributes.put("symbolicName", symbolicName);
              elementRecord.attributes.put("location", location.toString());
            }
          }

          private List<ElementRecord> getElementRecords(String point)
          {
            List<ElementRecord> elementRecords = result.get(point);
            if (elementRecords == null)
            {
              elementRecords = new ArrayList<ElementRecord>();
              result.put(point, elementRecords);
            }
            return elementRecords;
          }
        };

      // dump(result);
      return result;
    }

    private static void visitElement(Object[] children, List<ElementRecord> elementRecords)
    {
      // Visit the children.
      //
      for (Object child : children)
      {
        // if (child instanceof IPluginElement)
        //
        if (PLUGIN_ELEMENT_CLASS.isInstance(child))
        {
          elementRecords.add(visitElement(child));
        }
      }
    }

    private static ElementRecord visitElement(Object child)
    {
      ElementRecord elementRecord;
      if (PLUGIN_ELEMENT_CLASS.isInstance(child))
      {
        // child.getName();
        //
        String elementName = invoke(child, PLUGIN_OBJECT_GET_NAME_METHOD);

        // Record it by name.
        //
        elementRecord = new ElementRecord(elementName);

        // child.getAttributes()
        Object[] attributes = invoke(child, PLUGIN_ELEMENT_GET_ATTRIBUTES_METHOD);
        for (Object attribute : attributes)
        {
          // attribute.getName() and attribute.getValue()
          //
          String attributeName = invoke(attribute, PLUGIN_OBJECT_GET_NAME_METHOD);
          String attributeValue = invoke(attribute, PLUGIN_ATTRIBUTE_GET_VALUE_METHOD);

          // Record in the map.
          //
          elementRecord.attributes.put(attributeName, attributeValue);
        }
      }
      else
      {
        elementRecord = new ElementRecord("extension");
      }

      // Visit the child elements recursively.
      //
      Object[] elements = invoke(child, PLUGIN_EXTENSION_GET_CHILDREN_METHOD);
      if (elements.length != 0)
      {
        visitElement(elements, elementRecord.children);
      }

      return elementRecord;
    }

    private static abstract class ActiveModelVisitor
    {
      public ActiveModelVisitor() {
        // Iterate over all the active models in the workspace and target platform.
        //
        // IPluginModelBase[] activeModels = PluginRegistry.getActiveModels(false);
        //
        Object[] activeModels = invoke(null, PLUGIN_REGISTRY_GET_ACTIVE_MODELS_METHOD, Boolean.FALSE);
        if (activeModels != null)
        {
          for (Object activeModel : activeModels)
          {
            // Determine the symbolic name, underlying resource, if any, and the install location.
            //
            // BundleDescription bundleDescription = activeModel.getBundleDescription();
            // IResource underlyingResource = activeModel.getUnderlyingResource();
            // String installLocation = activeModel.getInstallLocation();
            //
            BundleDescription bundleDescription = invoke(activeModel, PLUGIN_MODEL_BASE_GET_BUNDLE_DESCRIPTION_METHOD);
            Object underlyingResource = invoke(activeModel, PLUGIN_MODEL_BASE_GET_UNDERLYING_RESOURCE_METHOD);
            String installLocation = (String)invoke(activeModel, PLUGIN_MODEL_BASE_GET_INSTALL_LOCATION_METHOD);
  
            // The URI for the location is determined from the underlying resource or the install location, with preference to the former if available.
            //
            URI location;
            if (underlyingResource != null)
            {
              // If there is an underlying resource, use the platform resource URI referencing the project in the workspace as the location.
              // underlyingResource.getProject()
              //
              Object project = invoke(underlyingResource, RESOURCE_GET_PROJECT_METHOD);
              IPath fullPath = invoke(project, RESOURCE_GET_FULL_PATH_METHOD);
              location = URI.createPlatformResourceURI(fullPath.toString(), true);
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
            else
            {
              location = null;
            }
  
            visitActiveModel(activeModel, bundleDescription, location);
          }
        }
      }

      abstract void visitActiveModel(Object activeModel, BundleDescription bundleDescription, URI location);
    }

//    private static void dump(Map<String, List<ElementRecord>> data)
//    {
//      for (Map.Entry<String, List<ElementRecord>> entry : data.entrySet())
//      {
//        System.out.println(entry.getKey());
//        dump(entry.getValue(), "  ");
//      }
//    }
//
//    private static void dump(List<ElementRecord> data, String indent)
//    {
//      for (ElementRecord elementRecord : data)
//      {
//        System.out.print(indent + "<" + elementRecord.getName());
//        for (Map.Entry<String, String> attribute : elementRecord.getAttributes().entrySet())
//        {
//          System.out.println();
//          System.out.print(indent + "    " + attribute.getKey() + "='" + attribute.getValue() + "'");
//        }
//        List<ElementRecord> children = elementRecord.getChildren();
//        if (children.isEmpty())
//        {
//        System.out.println("/>");
//        }
//        else
//        {
//        System.out.println(">");
//          dump(children, indent + "  ");
//        System.out.print(indent + "</" + elementRecord.getName() + ">");
//        }
//      }
//    }

  }
}
