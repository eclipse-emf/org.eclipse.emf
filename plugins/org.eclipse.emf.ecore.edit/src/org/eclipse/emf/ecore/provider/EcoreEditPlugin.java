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
package org.eclipse.emf.ecore.provider;


import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.RegistryFactory;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.plugin.RegistryReader;
import org.eclipse.emf.ecore.provider.annotation.EAnnotationItemProviderAdapterFactory;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;


/**
 * This is the central singleton for the Ecore edit plugin.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public final class EcoreEditPlugin extends EMFPlugin
{
  /**
   * Keep track of the singleton.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final EcoreEditPlugin INSTANCE = new EcoreEditPlugin();

  /**
   * Keep track of the singleton.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static Implementation plugin;

  /**
   * Create the instance.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EcoreEditPlugin()
  {
    super
      (new ResourceLocator [] 
       {
       });
  }

  /**
   * Returns the singleton instance of the Eclipse plugin.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the singleton instance.
   * @generated
   */
  @Override
  public ResourceLocator getPluginResourceLocator()
  {
    return plugin;
  }

  /**
   * Returns the singleton instance of the Eclipse plugin.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the singleton instance.
   * @generated
   */
  public static Implementation getPlugin()
  {
    return plugin;
  }

  /**
   * The actual implementation of the Eclipse <b>Plugin</b>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static class Implementation extends EclipsePlugin
  {
    /**
     * Creates an instance.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
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
      new AnnotationItemProviderAdapterFactoryRegistryReader().readRegistry();
    }

    /**
     * The actual implementation of the purely OSGi-compatible <b>Bundle Activator</b>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final class Activator extends EMFPlugin.OSGiDelegatingBundleActivator
    {
      @Override
      protected BundleActivator createBundle()
      {
        return new Implementation();
      }
    }
  }
}

class AnnotationItemProviderAdapterFactoryRegistryReader extends RegistryReader
{
  static class AnnotationItemProviderAdapterFactoryDescriptor extends PluginClassDescriptor implements EAnnotationItemProviderAdapterFactory.Factory
  {
    public AnnotationItemProviderAdapterFactoryDescriptor(IConfigurationElement e, String attrName)
    {
      super(e, attrName);
    }

    public EAnnotationItemProviderAdapterFactory createEAnnotationItemProviderAdapterFactory()
    {
      return (EAnnotationItemProviderAdapterFactory)createInstance();
    }
 
    public IConfigurationElement getElement()
    {
      return element;
    }
  }

  static final String TAG_FACTORY = "factory";
  static final String ATT_URI = "uri";
  static final String ATT_CLASS = "class";

  public AnnotationItemProviderAdapterFactoryRegistryReader()
  {
    super(RegistryFactory.getRegistry(), EcoreEditPlugin.INSTANCE.getSymbolicName(), "annotation_item_provider_adapter_factory");
  }

  @Override
  protected boolean readElement(IConfigurationElement element, boolean add)
  {
    if (element.getName().equals(TAG_FACTORY))
    {
      String uri = element.getAttribute(ATT_URI);
      if (uri == null)
      {
        logMissingAttribute(element, ATT_URI);
      }
      else if (element.getAttribute(ATT_CLASS) == null)
      {
        logMissingAttribute(element, ATT_CLASS);
      }
      else if (add)
      {
        Object previous = EAnnotationItemProviderAdapterFactory.Registry.INSTANCE.put(uri, new AnnotationItemProviderAdapterFactoryDescriptor(element, ATT_CLASS));
        if (previous instanceof AnnotationItemProviderAdapterFactoryDescriptor)
        {
          AnnotationItemProviderAdapterFactoryDescriptor descriptor = (AnnotationItemProviderAdapterFactoryDescriptor)previous;
          EcorePlugin.INSTANCE.log("Both '" + descriptor.getElement().getContributor().getName() + "' and '" + element.getContributor().getName() + "' register an annotation item provider adapter factory for '" + uri + "'");
        }
        return true;
      }
      else
      {
        EAnnotationItemProviderAdapterFactory.Registry.INSTANCE.remove(uri);
        return true;
      }
    }

    return false;
  }
}
