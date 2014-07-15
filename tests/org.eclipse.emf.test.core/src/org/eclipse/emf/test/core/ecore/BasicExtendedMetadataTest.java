/**
 * Copyright (c) 2014 CEA and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   CEA - Initial API and implementation
 */
package org.eclipse.emf.test.core.ecore;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.test.common.TestUtil;
import org.eclipse.emf.test.core.AllSuites;
import org.eclipse.emf.test.core.extmetadata.ExtmetadataPackage;
import org.junit.Test;


/**
 * <b>NOTE</b> that this class has a test case that requires no instance models described by
 * the {@link ExtmetadataPackage} to have been loaded prior to its execution. So, if any
 * other tests use that package, this test must be the first to run.
 */
public class BasicExtendedMetadataTest
{
  private static final String THING_NAMESPACE = "http://www.eclipse.org/emf/2014/test/thing";

  /**
   * Test that dynamic EPackages embedded in a user-model resource, added to a
   * resource set's local package registry, are not leaked in the extended
   * metadata of statically registered (generated) EPackages.
   *
   * @see https://bugs.eclipse.org/bugs/show_bug.cgi?id=433108
   */
  @Test
  public void testDynamicEPackageLeak()
  {
    // Initialize the static EPackage
    ExtmetadataPackage.eINSTANCE.eClass();

    // Load an instance model (which includes a dynamic Thing EPackage)
    ResourceSet rset = new ResourceSetImpl();
    rset.getLoadOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, true);
    rset.getLoadOptions().put(XMLResource.OPTION_LAX_FEATURE_PROCESSING, true);

    rset.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
    Resource res = rset.getResource(URI.createFileURI(TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/data/extmetadata.xmi"), true);

    // Find the dynamic Thing package
    EPackage thingPackage = rset.getPackageRegistry().getEPackage(THING_NAMESPACE);
    assertNotNull("Did not find Thing package", thingPackage);

    res.unload();
    assertTrue("Thing package was not unloaded", thingPackage.eIsProxy());

    // Look for the leaked thing package in the extended metadata of the static package
    BasicExtendedMetaData.EStructuralFeatureExtendedMetaData featureMetadata = ((BasicExtendedMetaData.EStructuralFeatureExtendedMetaData.Holder)ExtmetadataPackage.Literals.ATTRIBUTE__TYPE).getExtendedMetaData();
    if (featureMetadata != null)
    {
      ExtendedMetaData outerMetadata = getOuterClassInstance(featureMetadata, ExtendedMetaData.class);
      EPackage foundPackage = outerMetadata.getPackage(THING_NAMESPACE);

      // Either we should not find any Thing package, or it would be demand-created and thus be a different
      // instance of the Java EPackage class
      assertTrue("Thing package was leaked", (foundPackage == null) || (foundPackage != thingPackage));
    }
  }

  //
  // Test framework
  //

  <T> T getOuterClassInstance(Object o, Class<T> outerClass)
  {
    try
    {
      Field dollarsZero = o.getClass().getDeclaredField("this$0");
      dollarsZero.setAccessible(true);
      Object result = dollarsZero.get(o);
      assertTrue("Wrong outer class type", outerClass.isInstance(result));
      return outerClass.cast(result);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      fail("Could not get outer class instance: " + e.getLocalizedMessage());
      return null; // Unreachable
    }
  }
}
