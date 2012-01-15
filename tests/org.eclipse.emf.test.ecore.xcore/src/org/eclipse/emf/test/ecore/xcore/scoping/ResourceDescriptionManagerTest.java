/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.test.ecore.xcore.scoping;


import java.util.Iterator;

import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XcoreInjectorProvider;
import org.eclipse.xtext.common.types.TypesPackage;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.junit4.util.ParseHelper;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IResourceDescription;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.inject.Inject;

import static org.junit.Assert.*;


@RunWith(XtextRunner.class)
@InjectWith(XcoreInjectorProvider.class)
public class ResourceDescriptionManagerTest
{

  @Inject
  private ParseHelper<XPackage> parser;

  @Inject
  private IResourceDescription.Manager descriptionManager;

  @Test
  public void testCreateResourceDescription() throws Exception
  {
    XPackage xcorePackage = parser.parse("package foo.bar class Baz {}");
    IResourceDescription resourceDescription = descriptionManager.getResourceDescription(xcorePackage.eResource());

    Iterator<IEObjectDescription> eclass = resourceDescription.getExportedObjectsByType(EcorePackage.Literals.ECLASS).iterator();
    Iterator<IEObjectDescription> genclass = resourceDescription.getExportedObjectsByType(GenModelPackage.Literals.GEN_CLASS).iterator();
    Iterator<IEObjectDescription> jvmTypes = resourceDescription.getExportedObjectsByType(TypesPackage.Literals.JVM_GENERIC_TYPE).iterator();
    final String expected = "foo.bar.Baz";
    assertEquals(expected, eclass.next().getName().toString());
    assertFalse(eclass.hasNext());
    assertEquals(expected, genclass.next().getName().toString());
    assertFalse(genclass.hasNext());
    assertEquals(expected, jvmTypes.next().getName().toString());
    assertEquals(expected + "Impl", jvmTypes.next().getName().toString());
    assertFalse(genclass.hasNext());
  }

}
