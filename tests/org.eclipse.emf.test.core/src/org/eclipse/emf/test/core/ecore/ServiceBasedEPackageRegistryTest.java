package org.eclipse.emf.test.core.ecore;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.impl.EPackageRegistryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceRegistration;

public class ServiceBasedEPackageRegistryTest {

  private BundleContext context;
  private List<ServiceRegistration<Registry>> registeredServices = new ArrayList<>();
  private EPackage testPackage;

  @Before
  public void before() {
    if(EcorePlugin.USE_OSGI_SERVICE_AS_EPACKAGE_REGISTRY)
    {
      context = FrameworkUtil.getBundle(getClass()).getBundleContext();
      createTestPackage();
    }
  }

  private void createTestPackage() {
    testPackage = EcoreFactory.eINSTANCE.createEPackage();
    testPackage.setNsURI("http://test.de/test/1.0");
  }

  @After
  public void after() {
    if(EcorePlugin.USE_OSGI_SERVICE_AS_EPACKAGE_REGISTRY)
    {
      EPackage.Registry.INSTANCE.remove(testPackage.getNsURI());
      if (!registeredServices.isEmpty()) 
      {
        registeredServices.forEach(ServiceRegistration::unregister);
        registeredServices.clear();
        ;
      }
    }
  }

  /*
   * Tests that the Registered Registry is picked up and that changes to the
   * static Registry is picked up.
   */
  @Test
  public void testBasicRegistration() {
    if(EcorePlugin.USE_OSGI_SERVICE_AS_EPACKAGE_REGISTRY)
    {
      Registry staticRegistry = EPackage.Registry.INSTANCE;
      
      assertNotNull(staticRegistry);
      
      EPackage manualPackage = staticRegistry.getEPackage(testPackage.getNsURI());
      assertNull(manualPackage);
      
      staticRegistry.put(testPackage.getNsURI(), testPackage);
      
      manualPackage = staticRegistry.getEPackage(testPackage.getNsURI());
      assertNotNull(manualPackage);
      
      EPackageRegistryImpl registryService = new EPackageRegistryImpl();
      
      ServiceRegistration<Registry> registryServiceRegistration = context.registerService(Registry.class, registryService,
          FrameworkUtil.asDictionary(Collections.singletonMap("emf.default.epackage.registry", true)));
      
      registeredServices.add(registryServiceRegistration);
      
      manualPackage = registryService.getEPackage(testPackage.getNsURI());
      assertNotNull(manualPackage);
      
      staticRegistry.remove(testPackage.getNsURI());
      manualPackage = registryService.getEPackage(testPackage.getNsURI());
      assertNull(manualPackage);
      
      staticRegistry.put(testPackage.getNsURI(), testPackage);
      registryServiceRegistration.unregister();
      registeredServices.remove(registryServiceRegistration);
      
      manualPackage = staticRegistry.getEPackage(testPackage.getNsURI());
      assertNotNull(manualPackage);
    }
  }

  /*
   * The expectation is that a second Registry will be ignored
   */
  @Test
  public void testDoubleRegistration() {
    if(EcorePlugin.USE_OSGI_SERVICE_AS_EPACKAGE_REGISTRY)
    {
      Registry staticRegistry = EPackage.Registry.INSTANCE;
      
      assertNotNull(staticRegistry);
      
      EPackage manualPackage = staticRegistry.getEPackage(testPackage.getNsURI());
      assertNull(manualPackage);
      
      staticRegistry.put(testPackage.getNsURI(), testPackage);
      
      manualPackage = staticRegistry.getEPackage(testPackage.getNsURI());
      assertNotNull(manualPackage);
      
      EPackageRegistryImpl mainRegistry = new EPackageRegistryImpl();
      
      ServiceRegistration<Registry> registration1 = context.registerService(Registry.class, mainRegistry,
          FrameworkUtil.asDictionary(Collections.singletonMap("emf.default.epackage.registry", true)));
      registeredServices.add(registration1);
      
      manualPackage = mainRegistry.getEPackage(testPackage.getNsURI());
      assertNotNull(manualPackage);
      
      EPackageRegistryImpl toIgnoreRegistry = new EPackageRegistryImpl();
      
      ServiceRegistration<Registry> registration2 = context.registerService(Registry.class, mainRegistry,
          FrameworkUtil.asDictionary(Collections.singletonMap("emf.default.epackage.registry", true)));
      registeredServices.add(registration2);
      // we have to use something random in order to avoid interference between tests,
      // because of the static nature of the registry
      String random = UUID.randomUUID().toString();
      
      // just to make sure nothing lands there
      staticRegistry.put(random, testPackage);
      
      manualPackage = toIgnoreRegistry.getEPackage(random);
      assertNull(manualPackage);
    }
  }

  /*
   * The expectation is that a second Registry will replace the old registry, due
   * to the higher service rank
   */
  @Test
  public void testDoubleRegistrationWithServiceRank() {
    if(EcorePlugin.USE_OSGI_SERVICE_AS_EPACKAGE_REGISTRY)
    {
      Registry staticRegistry = EPackage.Registry.INSTANCE;
      
      assertNotNull(staticRegistry);
      
      EPackage manualPackage = staticRegistry.getEPackage(testPackage.getNsURI());
      assertNull(manualPackage);
      
      staticRegistry.put(testPackage.getNsURI(), testPackage);
      
      manualPackage = staticRegistry.getEPackage(testPackage.getNsURI());
      assertNotNull(manualPackage);
      
      EPackageRegistryImpl mainRegistry = new EPackageRegistryImpl();
      
      ServiceRegistration<Registry> registration1 = context.registerService(Registry.class, mainRegistry,
          FrameworkUtil.asDictionary(Collections.singletonMap("emf.default.epackage.registry", true)));
      
      registeredServices.add(registration1);
      
      manualPackage = mainRegistry.getEPackage(testPackage.getNsURI());
      assertNotNull(manualPackage);
      
      EPackageRegistryImpl secondRegistry = new EPackageRegistryImpl();
      
      Map<String, Object> props = new HashMap<>();
      props.put("emf.default.epackage.registry", true);
      props.put(Constants.SERVICE_RANKING, 100);
      ServiceRegistration<Registry> registration2 = context.registerService(Registry.class, secondRegistry,
          FrameworkUtil.asDictionary(props));
      registeredServices.add(registration2);
      
      manualPackage = secondRegistry.getEPackage(testPackage.getNsURI());
      assertNotNull(manualPackage);
      
      // we have to use something random in order to avoid interference between tests,
      // because of the static nature of the registry
      String random = UUID.randomUUID().toString();
      
      staticRegistry.put(random, testPackage);
      
      manualPackage = secondRegistry.getEPackage(random);
      assertNotNull(manualPackage);
      
      // Nothing must be added to the old registry
      manualPackage = mainRegistry.getEPackage(random);
      assertNull(manualPackage);
      
      // now we unregister the higher ranked registry and the test2 must be part of
      // the old one, as it becomes active again
      registration2.unregister();
      registeredServices.remove(registration2);
      
      manualPackage = mainRegistry.getEPackage(random);
      assertNotNull(manualPackage);
    }
  }

}