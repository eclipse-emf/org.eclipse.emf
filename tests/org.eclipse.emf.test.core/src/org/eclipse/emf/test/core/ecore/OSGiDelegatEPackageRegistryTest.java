/**
 * Copyright (c) 2025 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.test.core.ecore;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageRegistryImpl;
import org.junit.After;
import org.junit.Test;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.Filter;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;


@SuppressWarnings("unchecked")
public class OSGiDelegatEPackageRegistryTest
{
  private static final BundleContext BUNDLE_CONTEXT = FrameworkUtil.getBundle(OSGiDelegatEPackageRegistryTest.class).getBundleContext();

  private static final Filter FILTER;

  private static final Constructor<EPackage.Registry> OSGI_DELEGATE_PACKAGE_REGISTRY_CONSTRUCTOR;

  static
  {
    try
    {
      Class<?> osgiDelegatEPackageRegistryClass = CommonPlugin.loadClass("org.eclipse.emf.ecore", "org.eclipse.emf.ecore.plugin.EcorePlugin$OSGiDelegatEPackageRegistry");
      Field filterField = osgiDelegatEPackageRegistryClass.getDeclaredField("FILTER");
      filterField.setAccessible(true);
      FILTER = (Filter)filterField.get(null);
      OSGI_DELEGATE_PACKAGE_REGISTRY_CONSTRUCTOR = (Constructor<Registry>)osgiDelegatEPackageRegistryClass.getDeclaredConstructor(BundleContext.class);
      OSGI_DELEGATE_PACKAGE_REGISTRY_CONSTRUCTOR.setAccessible(true);
    }
    catch (Exception exception)
    {
      throw new RuntimeException(exception);
    }
  }

  private final List<ServiceTracker<?, ?>> trackers = new ArrayList<>();

  private final List<ServiceRegistration<?>> registrations = new ArrayList<>();

  @After
  public void teardown()
  {
    for (ServiceTracker<?, ?> serviceTracker : trackers)
    {
      serviceTracker.close();
    }

    for (ServiceRegistration<?> serviceRegistration : registrations)
    {
      try
      {
        serviceRegistration.unregister();
      }
      catch (IllegalStateException exception)
      {
      }
    }
  }

  private EPackage.Registry newInstance()
  {
    try
    {
      EPackage.Registry newInstance = OSGI_DELEGATE_PACKAGE_REGISTRY_CONSTRUCTOR.newInstance(BUNDLE_CONTEXT);
      ServiceTracker<?, ?> serviceTracker = new ServiceTracker<>(BUNDLE_CONTEXT, FILTER, (ServiceTrackerCustomizer<?, ?>)newInstance);
      trackers.add(serviceTracker);
      serviceTracker.open();
      return newInstance;
    }
    catch (Exception exception)
    {
      throw new RuntimeException(exception);
    }
  }

  private Dictionary<String, Object> newDictionary(int serviceRanking)
  {
    Dictionary<String, Object> properties = new Hashtable<>();
    properties.put("emf.default.epackage.registry", Boolean.TRUE);
    properties.put(Constants.SERVICE_RANKING, serviceRanking);
    return properties;
  }

  private ServiceRegistration<Registry> newServiceRegistration(EPackage.Registry registry, int serviceRanking)
  {
    ServiceRegistration<Registry> serviceRegistration = BUNDLE_CONTEXT.registerService(Registry.class, registry, newDictionary(serviceRanking));
    registrations.add(serviceRegistration);
    return serviceRegistration;
  }

  private void compare(EPackage.Registry registry1, EPackage.Registry registry2)
  {
    // Ensure that all the methods of the implementation are tested a part of testing for equality.
    assertEquals(registry1.isEmpty(), registry2.isEmpty());
    assertEquals(registry1.size(), registry2.size());
    assertEquals(registry1.entrySet(), registry2.entrySet());
    assertEquals(registry1.keySet(), registry2.keySet());
    assertEquals(registry1.values().size(), registry2.values().size());
    assertEquals(new HashSet<>(registry1.values()), new HashSet<>(registry2.values()));
    assertEquals(registry1, registry2);
  }

  private void validate(EPackage.Registry osgiEPackageRegistry)
  {
    // Ensure that all the methods of the implementation are tested.
    assertTrue(osgiEPackageRegistry.containsKey("foo"));
    assertTrue(osgiEPackageRegistry.containsValue(EcorePackage.eINSTANCE));
    assertEquals(EcorePackage.eINSTANCE, osgiEPackageRegistry.get("foo"));
    assertEquals(EcorePackage.eINSTANCE, osgiEPackageRegistry.remove("foo"));
    osgiEPackageRegistry.putAll(Collections.singletonMap("foo", EcorePackage.eINSTANCE));
    osgiEPackageRegistry.clear();
    assertTrue(osgiEPackageRegistry.isEmpty());
    osgiEPackageRegistry.putAll(Collections.singletonMap("foo", EcorePackage.eINSTANCE));
    assertEquals(EcorePackage.eINSTANCE, osgiEPackageRegistry.getEPackage("foo"));
    assertEquals(EcoreFactory.eINSTANCE, osgiEPackageRegistry.getEFactory("foo"));
  }

  @Test
  public void testAllImplementationDetails()
  {
    EPackage.Registry osgiEPackageRegistry = newInstance();
    EPackageRegistryImpl serviceRegistry0 = new EPackageRegistryImpl();

    // serviceRegistry0 not yet active so don't expect it to be populated.
    osgiEPackageRegistry.put("foo", EcorePackage.eINSTANCE);
    assertNotEquals(osgiEPackageRegistry, serviceRegistry0);

    // Ensure that all the methods are called before the service is registered.
    validate(osgiEPackageRegistry);
    compare(osgiEPackageRegistry, osgiEPackageRegistry);

    ServiceRegistration<Registry> serviceRegistration0 = newServiceRegistration(serviceRegistry0, 0);

    // Ensure that the basic methods are called after the service is registered.
    validate(osgiEPackageRegistry);

    // serviceRegistry0 is active so expect it to be populated.
    // Object service0 = tracker.addingService(serviceReference0);
    compare(osgiEPackageRegistry, serviceRegistry0);

    // serviceRegistry0 is active so expect it to be populated as the delegate is changed.
    osgiEPackageRegistry.put("foo1", EcorePackage.eINSTANCE);
    compare(osgiEPackageRegistry, serviceRegistry0);

    // serviceRegistry0 is deactivated so stop expecting changes to the delegate to be reflected.
    serviceRegistration0.unregister();
    osgiEPackageRegistry.put("foo2", EcorePackage.eINSTANCE);
    assertNotEquals(osgiEPackageRegistry, serviceRegistry0);

    // serviceRegistry1 with service ranking 1.
    EPackageRegistryImpl serviceRegistry1 = new EPackageRegistryImpl();
    ServiceRegistration<Registry> serviceRegistration1 = newServiceRegistration(serviceRegistry1, 1);

    // serviceRegistry2 with service ranking 2.
    EPackageRegistryImpl serviceRegistry2 = new EPackageRegistryImpl();
    @SuppressWarnings("unused")
    ServiceRegistration<Registry> serviceRegistration2 = newServiceRegistration(serviceRegistry2, 2);

    // serviceRegistry2 with service ranking 2 should have priority so serviceRegistry1 should not reflect the addition while serviceRegistry2 should.
    osgiEPackageRegistry.put("foo3", EcorePackage.eINSTANCE);
    assertNotEquals(osgiEPackageRegistry, serviceRegistry1);
    compare(osgiEPackageRegistry, serviceRegistry2);

    // Change the service ranking of serviceRegistration1.
    serviceRegistration1.setProperties(newDictionary(3));

    // serviceRegistry1 now with service ranking 3 should have priority so serviceRegistry2 should not reflect the addition while serviceRegistry1 should.
    osgiEPackageRegistry.put("foo4", EcorePackage.eINSTANCE);
    compare(osgiEPackageRegistry, serviceRegistry1);
    assertNotEquals(osgiEPackageRegistry, serviceRegistry2);
  }

}