/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: EMFPropertiesTest.java,v 1.1 2009/11/25 09:15:06 tschindl Exp $
 */
package org.eclipse.emf.test.databinding;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.ListDiffEntry;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.databinding.observable.set.ISetChangeListener;
import org.eclipse.core.databinding.observable.set.SetChangeEvent;
import org.eclipse.core.databinding.observable.set.SetDiff;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.databinding.EMFProperties;
import org.eclipse.emf.databinding.IEMFSetProperty;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.test.common.TestUtil;
import org.eclipse.emf.test.databinding.emfdb.A;
import org.eclipse.emf.test.databinding.emfdb.B;
import org.eclipse.emf.test.databinding.emfdb.EmfdbFactory;
import org.eclipse.emf.test.databinding.emfdb.EmfdbPackage;

import junit.framework.TestCase;

public class EMFPropertiesTest extends TestCase
{
  private Resource resource;
  private Realm testRealm;
  private boolean flag;
  private SetDiff diff;

  @Override
  protected void setUp() throws Exception
  {
    super.setUp();

    ResourceSet resourceSet = new ResourceSetImpl();
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(
      Resource.Factory.Registry.DEFAULT_EXTENSION,
      new XMIResourceFactoryImpl());

    URI uri = URI.createFileURI(TestUtil.getPluginDirectory("org.eclipse.emf.test.databinding") + "/model/A.xmi");
    resource = resourceSet.getResource(uri, true);
    testRealm = new Realm()
      {

        @Override
        public boolean isCurrent()
        {
          return true;
        }
      };
    flag = false;
  }

  public void testSetProperty() {
    Realm.runWithDefault(testRealm, new Runnable()
      {

        public void run()
        {
          _testSetProperty();
        }
      });
  }

  public void _testSetProperty() {
    A a = (A)resource.getContents().get(0);
    IEMFSetProperty prop = EMFProperties.set(EmfdbPackage.Literals.A__BLIST);
    IObservableSet set = prop.observe(a);
    assertNotNull(set);
    set.addSetChangeListener(new ISetChangeListener()
      {

        public void handleSetChange(SetChangeEvent event)
        {
          diff = event.diff;
        }
      });
    assertNull(diff);
    B b = EmfdbFactory.eINSTANCE.createB();
    a.getBlist().add(b);
    assertNotNull(diff);
    assertEquals(1, diff.getAdditions().size());
    assertSame(b, diff.getAdditions().iterator().next());
  }
}
