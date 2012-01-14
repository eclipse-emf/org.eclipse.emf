/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.ecore;


import org.osgi.framework.BundleContext;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

import org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter;
import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.plugin.RegistryReader;
import org.eclipse.emf.ecore.resource.ResourceSet;


/**
 * The <b>Plugin</b> for the EMF.CodeGen.Ecore library.
 */
public final class CodeGenEcorePlugin extends EMFPlugin 
{
  /**
   * The singleton instance of the plugin.
   */
  public static final CodeGenEcorePlugin INSTANCE = new CodeGenEcorePlugin();

  /**
   * The one instance of this class.
   */
  private static Implementation plugin;

  public static final String ID = "org.eclipse.emf.codegen.ecore";

  /**
   * Creates the singleton instance.
   */
  private CodeGenEcorePlugin()
  {
    super(new ResourceLocator[] {});
  }

  /*
   * Javadoc copied from base class.
   */
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
   * The actual implementation of the Eclipse <b>Plugin</b>.
   */
  public static class Implementation extends EMFPlugin.EclipsePlugin 
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

    @Override
    public void start(BundleContext context) throws Exception
    {
      super.start(context);

      new GeneratorAdaptersRegistryReader().readRegistry();
    }
  }

  static class GeneratorAdaptersRegistryReader extends RegistryReader
  {
    static final String EXTENSION_POINT_ID = "generatorAdapters";
    static final String ADAPTER_FACTORY_ELEMENT = "adapterFactory";
    static final String ADAPTER_ELEMENT = "adapter";
    static final String MODEL_PACKAGE_ATTRIBUTE = "modelPackage";
    static final String MODEL_CLASS_ATTRIBUTE = "modelClass";
    static final String CLASS_ATTRIBUTE = "class";

    public GeneratorAdaptersRegistryReader()
    {
      super(Platform.getExtensionRegistry(), getPlugin().getBundle().getSymbolicName(), EXTENSION_POINT_ID);
    }

    @Override
    protected boolean readElement(IConfigurationElement element)
    {
      String name = element.getName();

      if (ADAPTER_FACTORY_ELEMENT.equals(name))
      {
        String modelPackage = element.getAttribute(MODEL_PACKAGE_ATTRIBUTE);
        if (modelPackage == null)
        {
          modelPackage = GenModelPackage.eNS_URI;
        }

        if (element.getAttribute(CLASS_ATTRIBUTE) == null)
        {
          logMissingAttribute(element, CLASS_ATTRIBUTE);
        }
        else
        {
          GeneratorAdapterFactory.Descriptor.Registry.INSTANCE.addDescriptor(modelPackage, new Descriptor(element));
          return true;
        }
      }

      if (ADAPTER_ELEMENT.equals(name))
      {
        String modelPackage = element.getAttribute(MODEL_PACKAGE_ATTRIBUTE);
        String modelClass = element.getAttribute(MODEL_CLASS_ATTRIBUTE);
        if (modelPackage == null)
        {
          modelPackage = GenModelPackage.eNS_URI;
        }

        if (modelClass == null)
        {
          logMissingAttribute(element, MODEL_CLASS_ATTRIBUTE);
        }
        else if (element.getAttribute(CLASS_ATTRIBUTE) == null)
        {
          logMissingAttribute(element, CLASS_ATTRIBUTE);
        }
        else
        {
          GeneratorAdapterFactory.Descriptor.Registry.INSTANCE.addDescriptor
            (modelPackage, new GenericDescriptor(modelPackage, modelClass, element));
          return true;
        }
      }
      return false;
    }

    static class Descriptor extends RegistryReader.PluginClassDescriptor implements GeneratorAdapterFactory.Descriptor
    {
      public Descriptor(IConfigurationElement element)
      {
        super(element, CLASS_ATTRIBUTE);
      }

      public GeneratorAdapterFactory createAdapterFactory()
      {
        return (GeneratorAdapterFactory)createInstance();
      }
    }

    static class GenericDescriptor extends RegistryReader.PluginClassDescriptor implements GeneratorAdapterFactory.Descriptor
    {
      class AdapterFactory extends AbstractGeneratorAdapterFactory
      {
        protected GeneratorAdapter adapter;

        @Override
        protected Adapter createAdapter(Notifier target)
        {
          ResourceSet resourceSet = getGenerator().getOptions().resourceSet;
          EPackage.Registry packageRegistry = resourceSet != null ? resourceSet.getPackageRegistry() : EPackage.Registry.INSTANCE;
          EPackage ePackage = packageRegistry.getEPackage(modelPackage);
          if (ePackage != null)
          {
            EClassifier eClassifier = ePackage.getEClassifier(modelClass);
            if (eClassifier.isInstance(target))
            {
              adapter = (GeneratorAdapter)createInstance();
              return (Adapter)createInstance();
            }
          }
          return null;
        }

        @Override
        protected GeneratorAdapter createAdapter(Object object)
        {
          try
          {
            if (Class.forName(modelPackage + "." + modelClass).isInstance(object))
            {
              adapter = (GeneratorAdapter)createInstance();
              return adapter;
            }
          }
          catch (ClassNotFoundException e)
          {
            // Ignore
          }

          return null;
        }

        @Override
        public void dispose()
        {
          if (adapter != null) adapter.dispose();
        }
      }

      private String modelPackage;
      private String modelClass;

      public GenericDescriptor(String modelPackage, String modelClass, IConfigurationElement element)
      {
        super(element, CLASS_ATTRIBUTE);
        this.modelPackage = modelPackage;
        this.modelClass = modelClass;
      }

      public GeneratorAdapterFactory createAdapterFactory()
      {
        return new AdapterFactory();
      }
    }
  }
}
