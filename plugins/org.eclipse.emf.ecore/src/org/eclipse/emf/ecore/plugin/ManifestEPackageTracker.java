/**
 * Copyright (c) 2025-2025 IILS mbH and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors: 
 *   Hannes Wellmann (IILS mbH) - Initial API and implementation
 */
package org.eclipse.emf.ecore.plugin;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.plugin.ManifestEPackageTracker.ManifestEPackageDescriptor;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.osgi.util.ManifestElement;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleException;
import org.osgi.framework.Constants;
import org.osgi.framework.wiring.BundleRevision;
import org.osgi.resource.Capability;
import org.osgi.resource.Resource;
import org.osgi.util.tracker.BundleTracker;


/**
 * An {@link BundleTracker OSGi-BundleTracker} that reads and registers the EPackages generated into  {@code META-INF/MANIFEST.MF} files as provided OSGi capabilities:
 *
 * <pre>
 *  Provide-Capability:
 *   org.eclipse.emf.ecore.generated_package;
 *     uri=http://foo.bar/packA;
 *     class=packA.PackAPackage;
 *     genModel="model/packA.genmodel"
 * </pre>
 */
class ManifestEPackageTracker extends BundleTracker<List<ManifestEPackageDescriptor>>
{
  public static final String GENERATED_PACKAGE_NAMESPACE = "org.eclipse.emf.ecore.generated_package";

  public static final String ATTRIBUTE_URI = "uri";

  public static final String ATTRIBUTE_CLASS = "class";

  public static final String ATTRIBUTE_GEN_MODEL = "genModel";

  static void registerManifestPackagesIfAbsent(URI manifest, ClassLoader classLoader) throws IOException, BundleException
  {
    try (InputStream content = URIConverter.INSTANCE.createInputStream(manifest))
    {
      List<ManifestEPackageDescriptor> descriptors = ManifestEPackageDescriptor.parse(content, classLoader);
      for (ManifestEPackageDescriptor descriptor : descriptors)
      {
        registerIfAbsent(descriptor, genModel -> manifest.trimSegments(2).appendFileExtension(genModel));
      }
    }
  }

  ManifestEPackageTracker(BundleContext context)
  {
    // TODO: check if stopping and (re-)starting creates a new Classloader?! But re-wiring does?!
    super(context, Bundle.RESOLVED | Bundle.STARTING | Bundle.ACTIVE | Bundle.STOPPING, null);
  }

  @Override
  public List<ManifestEPackageDescriptor> addingBundle(Bundle bundle, BundleEvent event)
  {
    Resource resource = bundle.adapt(BundleRevision.class);
    List<ManifestEPackageDescriptor> descriptors = new ArrayList<>();
    for (Capability capability : resource.getCapabilities(GENERATED_PACKAGE_NAMESPACE))
    {
      ManifestEPackageDescriptor descriptor = ManifestEPackageDescriptor.parse(bundle, capability);
      if (registerIfAbsent(descriptor, genModel -> {
        String path = bundle.getSymbolicName() + "/" + genModel;
        return URI.createPlatformPluginURI(path, true);
      }))
      {
        descriptors.add(descriptor);
      }
    }
    return !descriptors.isEmpty() ? Collections.unmodifiableList(descriptors) : null;
  }

  private static boolean registerIfAbsent(ManifestEPackageDescriptor descriptor, Function<String, URI> absoluteGenModelURIFactory)
  {
    String packageURI = descriptor.packageURI;
    if (EPackage.Registry.INSTANCE.putIfAbsent(packageURI, descriptor) == null)
    {
      // Only add if absent to grant precedence for registrations through a plugin.xml

      String genModel = descriptor.genModel;
      URI genModelURI = URI.createURI(genModel);
      if (genModelURI.isRelative())
      {
        genModelURI = absoluteGenModelURIFactory.apply(genModel);
      }
      EcorePlugin.getEPackageNsURIToGenModelLocationMap(false).put(packageURI, genModelURI);
      return true;
    }
    return false;
  }

  @Override
  public void removedBundle(Bundle bundle, BundleEvent event, List<ManifestEPackageDescriptor> packages)
  {
    for (ManifestEPackageDescriptor descriptor : packages)
    {
      String uri = descriptor.packageURI;
      EPackage.Registry.INSTANCE.remove(uri);
      EcorePlugin.getEPackageNsURIToGenModelLocationMap(false).remove(uri);
    }
  }

  private static interface ClassLoaderWrapper
  {
    Class<?> loadClass(String name) throws ClassNotFoundException;
  }

  static class ManifestEPackageDescriptor implements EPackage.Descriptor
  {

    static ManifestEPackageDescriptor parse(Bundle bundle, Capability capability)
    {
      Map<String, Object> attributes = capability.getAttributes();
      return new ManifestEPackageDescriptor(
        bundle::loadClass,
        (String)attributes.get(ATTRIBUTE_URI),
        (String)attributes.get(ATTRIBUTE_CLASS),
        (String)attributes.get(ATTRIBUTE_GEN_MODEL));
    }

    static List<ManifestEPackageDescriptor> parse(InputStream manifestContent, ClassLoader classLoader) throws IOException, BundleException
    {
      Map<String, String> bundleManifest = ManifestElement.parseBundleManifest(manifestContent, null);
      String value = bundleManifest.get(Constants.PROVIDE_CAPABILITY);
      if (value == null || value.trim().isEmpty())
      {
        return Collections.emptyList();
      }
      ManifestElement[] elements = ManifestElement.parseHeader(Constants.PROVIDE_CAPABILITY, value.trim());
      if (elements == null)
      {
        return Collections.emptyList();
      }
      List<ManifestEPackageDescriptor> descriptors = new ArrayList<>();
      for (ManifestElement element : elements)
      {
        if (GENERATED_PACKAGE_NAMESPACE.equals(element.getValue()))
        {
          ManifestEPackageDescriptor descriptor = new ManifestEPackageDescriptor(
            classLoader::loadClass,
            element.getAttribute(ATTRIBUTE_URI),
            element.getAttribute(ATTRIBUTE_CLASS),
            element.getAttribute(ATTRIBUTE_GEN_MODEL));
          descriptors.add(descriptor);
        }
      }
      return descriptors;
    }

    final ClassLoaderWrapper classLoader;

    final String packageURI;

    final String packageClassName;

    final String genModel;

    ManifestEPackageDescriptor(Bundle bundle, String packageURI, String packageClassName, String genModel)
    { // Don't use current Wiring ClassLoader directly to allow changes until the first EPackage is eventually created
      this(bundle::loadClass, packageURI, packageClassName, genModel);
    }

    ManifestEPackageDescriptor(ClassLoader classLoader, String packageURI, String packageClassName, String genModel)
    {
      this(classLoader::loadClass, packageURI, packageClassName, genModel);
    }

    private ManifestEPackageDescriptor(ClassLoaderWrapper classLoader, String packageURI, String packageClassName, String genModel)
    {
      this.classLoader = classLoader;
      this.packageURI = packageURI;
      this.packageClassName = packageClassName;
      this.genModel = genModel;
    }

    @Override
    public EPackage getEPackage()
    {
      try
      {
        Class<?> packageClass = classLoader.loadClass(packageClassName);
        return (EPackage)packageClass.getField("eINSTANCE").get(null);
      }
      catch (ReflectiveOperationException e)
      {
        throw new WrappedException(e);
      }
    }

    @Override
    public EFactory getEFactory()
    {
      EPackage ePackage = getEPackage();
      return ePackage != null ? ePackage.getEFactoryInstance() : null;
    }
  }

}
