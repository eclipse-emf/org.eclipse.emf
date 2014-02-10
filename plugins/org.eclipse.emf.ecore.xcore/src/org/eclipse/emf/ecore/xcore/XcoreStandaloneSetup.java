/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore;


import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.xtext.GenModelSupport;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xml.namespace.XMLNamespacePackage;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.eclipse.xtext.common.types.TypesPackage;
import org.eclipse.xtext.ecore.EcoreSupportStandaloneSetup;
import org.eclipse.xtext.mwe.ContainersStateFactory;
import org.eclipse.xtext.mwe.PathTraverser;
import org.eclipse.xtext.mwe.RuntimeResourceSetInitializer;
import org.eclipse.xtext.mwe.UriFilter;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.resource.containers.IAllContainersState;
import org.eclipse.xtext.xbase.XbasePackage;

import com.google.common.base.Predicate;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;


/**
 * Initialization support for running Xtext languages
 * without equinox extension registry
 */
public class XcoreStandaloneSetup extends XcoreStandaloneSetupGenerated
{
  public static void doSetup()
  {
    new XcoreStandaloneSetup().createInjectorAndDoEMFRegistration();
  }

  @Override
  public Injector createInjectorAndDoEMFRegistration()
  {
    EcoreSupportStandaloneSetup.setup();

    new GenModelSupport().registerServices(false);

    return super.createInjectorAndDoEMFRegistration();
  }

  @Override
  public void register(Injector injector)
  {
    EPackage.Registry packageRegistry = injector.getInstance(EPackage.Registry.class);
    packageRegistry.put(XcorePackage.eNS_URI, XcorePackage.eINSTANCE);
    packageRegistry.put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);
    packageRegistry.put(XbasePackage.eNS_URI, XbasePackage.eINSTANCE);
    packageRegistry.put(TypesPackage.eNS_URI, TypesPackage.eINSTANCE);
    packageRegistry.put(GenModelPackage.eNS_URI, GenModelPackage.eINSTANCE);
    packageRegistry.put(XMLTypePackage.eNS_URI, XMLTypePackage.eINSTANCE);
    packageRegistry.put(XMLNamespacePackage.eNS_URI, XMLNamespacePackage.eINSTANCE);

    EcoreResourceFactoryImpl resourceFactory = new EcoreResourceFactoryImpl();
    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", resourceFactory);
    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("genmodel", resourceFactory);

    super.register(injector);
  }

  @Override
  public Injector createInjector()
  {
    return Guice.createInjector(new XcoreStandaloneRuntimeModule());
  }

  public static class XcoreStandaloneRuntimeModule extends XcoreRuntimeModule
  {
    @Override
    public Class<? extends XtextResourceSet> bindXtextResourceSet()
    {
      return null;
    }

    @Override
    public void configure(Binder binder)
    {
      binder.bind(XtextResourceSet.class).toProvider(XcoreStandaloneResourceSetProvider.class);

      super.configure(binder);
    }

    public Class<? extends PathTraverser> bindPathTraverser()
    {
      return XcorePathTraverser.class;
    }

    public static class XcoreStandaloneResourceSetProvider implements Provider<XtextResourceSet>
    {
      @Inject
      XcoreResourceSetInitializer resourceSetInitializer;

      public XtextResourceSet get()
      {
        return resourceSetInitializer.getInitializedResourceSet();
      }
    }

    public static class XcorePathTraverser extends PathTraverser
    {
      @Override
      public Set<URI> findAllResourceUris(String path, Predicate<URI> isValidPredicate)
      {
        Set<URI> result = super.findAllResourceUris(path, isValidPredicate);

        // Index the containing folder if there is a build.properties and we've not indexed anything in the bin folder.
        //
        if (result.isEmpty())
        {
          if (new File(path + "/../build.properties").isFile())
          {
            try
            {
              return traverseDir(new File(path + "/..").getCanonicalFile(), isValidPredicate);
            }
            catch (IOException exception)
            {
              // Ignore.
            }
          }
        }
        return result;
      }
    }

    public static class XcoreResourceSetInitializer extends RuntimeResourceSetInitializer
    {
      private static List<String> paths;
      private static Multimap<String, URI> pathToUriMap;

      @Inject
      Injector injector;

      @Inject
      private ContainersStateFactory factory;

      @Override
      public List<String> getClassPathEntries()
      {
        if (paths == null)
        {
          paths = super.getClassPathEntries();
        }
        return paths;
      }

      protected Multimap<String, URI> getPathToUriMap()
      {
        if (pathToUriMap == null)
        {
          pathToUriMap =
            getPathToUriMap
              (getClassPathEntries(), 
               new UriFilter()
               {
                 Set<String> fileExtensions = Sets.newHashSet("xcore", "genmodel", "ecore");
                 public boolean matches(URI uri)
                 {
                   return fileExtensions.contains(uri.fileExtension());
                 }
               });
        }
        return pathToUriMap;
      }

      public IAllContainersState getContainersState()
      {
        return factory.getContainersState(paths, pathToUriMap);
      }

      public class AllContainerAdapter extends AdapterImpl implements IAllContainersState
      {
        protected ResourceSet resourceSet;
        protected IAllContainersState allContainersState;

        public AllContainerAdapter(XtextResourceSet resourceSet)
        {
          this.resourceSet = resourceSet;
        }

        @Override
        public boolean isAdapterForType(Object type)
        {
          return IAllContainersState.class == type;
        }

        public boolean isEmpty(String containerHandle)
        {
          return getAllContainersState().isEmpty(containerHandle);
        }

        public List<String> getVisibleContainerHandles(String handle)
        {
          return getAllContainersState().getVisibleContainerHandles(handle);
        }

        public Collection<URI> getContainedURIs(String containerHandle)
        {
          return getAllContainersState().getContainedURIs(containerHandle);
        }

        public String getContainerHandle(URI uri)
        {
          return getAllContainersState().getContainerHandle(uri);
        }

        IAllContainersState getAllContainersState()
        {
          if (allContainersState == null)
          {
            List<String> classPathEntries = Lists.newArrayList(getClassPathEntries());
            classPathEntries.add("resourceSet");
            Multimap<String, URI> result = HashMultimap.create();
            for (Resource resource : resourceSet.getResources())
            {
              result.put("resourceSet", resourceSet.getURIConverter().normalize(resource.getURI()));
            }

            Multimap<String, URI> pathToUriMap = getPathToUriMap();
            for (URI uri : pathToUriMap.values())
            {
              resourceSet.createResource(uri);
            }
            result.putAll(pathToUriMap);

            allContainersState = factory.getContainersState(classPathEntries, result);
          }
          return allContainersState;
        }
      }

      public XtextResourceSet getInitializedResourceSet()
      {
        XtextResourceSet resourceSet = new XtextResourceSet();
        injector.injectMembers(resourceSet);
        resourceSet.eAdapters().add(new AllContainerAdapter(resourceSet));
        return resourceSet;
      }
    }
  }
}
