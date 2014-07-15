/**
 * Copyright (c) 2005-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.edit.provider;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.test.models.ext.ExtE;
import org.eclipse.emf.test.models.ext.ExtFactory;
import org.eclipse.emf.test.models.ext.ExtPackage;
import org.eclipse.emf.test.models.ext.F;
import org.eclipse.emf.test.models.ext.provider.ExtEItemProvider;
import org.eclipse.emf.test.models.ext.provider.ExtItemProviderAdapterFactory;
import org.eclipse.emf.test.models.ext.provider.FItemProvider;
import org.eclipse.emf.test.models.ref.A;
import org.eclipse.emf.test.models.ref.E;
import org.eclipse.emf.test.models.ref.RefFactory;
import org.eclipse.emf.test.models.ref.RefPackage;
import org.eclipse.emf.test.models.ref.provider.AItemProvider;
import org.eclipse.emf.test.models.ref.provider.EItemProvider;
import org.eclipse.emf.test.models.ref.provider.RefItemProviderAdapterFactory;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for ComposedAdapterFactory.
 */
public class ComposedAdapterFactoryTest
{
  /**
   * The Ref test package.
   */
  protected RefPackage refPackage;

  /**
   * The Ext test package.
   */
  protected ExtPackage extPackage;

  /**
   * The Ref factory.
   */
  protected RefFactory refFactory;

  /**
   * The Ext factory.
   */
  protected ExtFactory extFactory;

  /**
   * The Ref adapter factory.
   */
  protected RefItemProviderAdapterFactory refAdapterFactory;

  /**
   * The Ext adapter factory.
   */
  protected ExtItemProviderAdapterFactory extAdapterFactory;

  /**
   * A composed adapter factory for the two test packages.
   */
  protected ComposedAdapterFactory adapterFactory;

  @Before
  public void setUp() throws Exception
  {
    refPackage = RefPackage.eINSTANCE;
    refFactory = refPackage.getRefFactory();
    refAdapterFactory = new RefItemProviderAdapterFactory();

    extPackage = ExtPackage.eINSTANCE;
    extFactory = extPackage.getExtFactory();
    extAdapterFactory = new ExtItemProviderAdapterFactory();
  }

  @Test
  public void testAdapt()
  {
    List<AdapterFactory> factories = new ArrayList<AdapterFactory>();
    factories.add(refAdapterFactory);
    factories.add(extAdapterFactory);
    adapterFactory = new ComposedAdapterFactory(factories);
    Object type = IEditingDomainItemProvider.class;

    A a = refFactory.createA();
    Adapter aAdapter = adapterFactory.adapt(a, type);
    assertTrue(aAdapter instanceof AItemProvider);
    assertSame(refAdapterFactory, ((AItemProvider)aAdapter).getAdapterFactory());
    assertSame(refAdapterFactory.createAAdapter(), aAdapter);  // okay since singleton

    F f = extFactory.createF();
    Adapter fAdapter = adapterFactory.adapt(f, type);
    assertTrue(fAdapter instanceof FItemProvider);
    assertSame(extAdapterFactory, ((FItemProvider)fAdapter).getAdapterFactory());
    assertSame(extAdapterFactory.createFAdapter(), fAdapter);  // okay since singleton
  }

  @Test
  public void testAdaptSubclass()
  {
    List<AdapterFactory> factories = new ArrayList<AdapterFactory>();
    factories.add(refAdapterFactory);
    factories.add(extAdapterFactory);
    adapterFactory = new ComposedAdapterFactory(factories);
    Object type = IEditingDomainItemProvider.class;

    E e = refFactory.createE();
    Adapter eAdapter = adapterFactory.adapt(e, type);
    assertTrue(eAdapter instanceof EItemProvider);
    assertSame(refAdapterFactory, ((EItemProvider)eAdapter).getAdapterFactory());
    assertSame(refAdapterFactory.createEAdapter(), eAdapter);  // okay since singleton

    ExtE extE = extFactory.createExtE();
    Adapter extEAdapter = adapterFactory.adapt(extE, type);
    assertTrue(extEAdapter instanceof ExtEItemProvider);
    assertSame(extAdapterFactory, ((ExtEItemProvider)extEAdapter).getAdapterFactory());
    assertSame(extAdapterFactory.createExtEAdapter(), extEAdapter);  // okay since singleton
  }

  @Test
  public void testAdaptBaseclass()
  {
    adapterFactory = new ComposedAdapterFactory(refAdapterFactory);
    Object type = IEditingDomainItemProvider.class;

    ExtE extE = extFactory.createExtE();
    Adapter adapter = adapterFactory.adapt(extE, type);
    assertTrue(adapter instanceof EItemProvider);
    assertFalse(adapter instanceof ExtEItemProvider);
    assertSame(refAdapterFactory, ((EItemProvider)adapter).getAdapterFactory());
    assertSame(refAdapterFactory.createEAdapter(), adapter);  // okay since singleton
  }
}
