/**
 * Copyright (c) 2002-2019 IBM Corporation, CEA, and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *   Christian W. Damus (CEA) - 433108, 433027
 *   Thales - bug 543340
 */
package org.eclipse.emf.test.core;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses
  ({
      org.eclipse.emf.test.core.ecore.BasicExtendedMetadataTest.class,
      org.eclipse.emf.test.core.ecore.EcoreValidationTest.class,
      org.eclipse.emf.test.core.ecore.EcoreTest.class,
      org.eclipse.emf.test.core.common.NotificationTest.class,
      org.eclipse.emf.test.core.common.EnumeratorSerialization.class,
      org.eclipse.emf.test.core.common.util.EqualityTest.class,
      org.eclipse.emf.test.core.common.util.ECollectionsTest.class,
      org.eclipse.emf.test.core.common.util.EMapTest.class,
      org.eclipse.emf.test.core.dynamic.SimpleModelTest.class,
      org.eclipse.emf.test.core.dynamic.SimpleModelTest2.class,
      org.eclipse.emf.test.core.dynamic.SimpleModelTest3.class,
      org.eclipse.emf.test.core.dynamic.PermissiveDynamicModelTest.class,
      org.eclipse.emf.test.core.dynamic.ModelNotificationTest.class,
      org.eclipse.emf.test.core.dynamic.DynamicCrossResourceContainmentProxy.class,
      org.eclipse.emf.test.core.change.ChangeAllSuites.class,
      org.eclipse.emf.test.core.ecore.EcoreUtilStaticMethodsTest.class,
      org.eclipse.emf.test.core.ecore.ListTest.class,
      org.eclipse.emf.test.core.ecore.ResourceCacheMechanismTest.class,
      org.eclipse.emf.test.core.ecore.ContentTypeTest.class,
      org.eclipse.emf.test.core.ecore.DateConversionDelegateTest.class,
      org.eclipse.emf.test.core.ecore.EContentAdapterTest.class,
      org.eclipse.emf.test.core.ecore.IterativeEContentAdapterTest.class,
      org.eclipse.emf.test.core.ecore.PersistenceTest.class,
      org.eclipse.emf.test.core.ecore.ProxyURITest.class,
      org.eclipse.emf.test.core.ecore.ResourceAttachmentTest.class,
      org.eclipse.emf.test.core.ecore.FeatureMapTest.class,
      org.eclipse.emf.test.core.ecore.EClassGenericTypeCacheTest.class,
      org.eclipse.emf.test.core.ecore.DiagnosticianTest.class,
      org.eclipse.emf.test.core.ecore.ValidationTest.class,
      org.eclipse.emf.test.core.ecore.KeyTest.class,
      org.eclipse.emf.test.core.ecore.URIConverterTest.class,
      org.eclipse.emf.test.core.ecore.ResourceLocatorTest.class,
      org.eclipse.emf.test.core.ecore.GenericTypeBuilderTest.class,
      org.eclipse.emf.test.core.featuremap.FeatureMapTest.class,
      org.eclipse.emf.test.core.ecore.BadEcoreDiagnosticTest.class,
      org.eclipse.emf.test.core.ecore.BadURIFragmentTest.class,
      org.eclipse.emf.test.core.ecore.XMLTypeTest.class,
      org.eclipse.emf.test.core.ecore.BinaryResourceTest.class,
      org.eclipse.emf.test.core.ecore.SwitchTest.class,
      org.eclipse.emf.test.core.ecore.ResourceURIFragmentsTest.class,
      org.eclipse.emf.test.core.ecore.ResourceSetMappedResourceLocatorTest.class,
      org.eclipse.emf.test.core.ecore.ReificationTest.class,
      org.eclipse.emf.test.core.ecore.ECrossReferenceAdapterTest.class,
      org.eclipse.emf.test.core.ecore.ECrossReferenceAdapterStressTest.class,
      org.eclipse.emf.test.core.ecore.ECrossReferenceAdapterByFeatureTest.class,
      org.eclipse.emf.test.core.common.util.WeakInterningHashSetTest.class,
      org.eclipse.emf.test.core.common.util.PoolTest.class,
      org.eclipse.emf.test.core.common.util.StringPoolTest.class,
      org.eclipse.emf.test.core.common.util.SegmentSequenceTest.class,
      org.eclipse.emf.test.core.common.util.URITest.class
  })
public class AllSuites
{
  public static final String PLUGIN_ID = "org.eclipse.emf.test.core";
}