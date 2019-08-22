/**
 * Copyright (c) 2019 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributed via https://bugs.eclipse.org/bugs/show_bug.cgi?id=550338
 */
package org.eclipse.emf.test.core.ecore;


import static java.util.Arrays.*;
import static org.junit.Assert.*;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EClassImpl;
import org.junit.Test;


/**
 * Test for super type adapter in {@link EClassImpl}.
 */
public class EClassGenericTypeCacheTest
{
  @Test
  public void testEClassImplSuperTypeAdapter()
  {
    EClass eClass = EcoreFactory.eINSTANCE.createEClass();

    // add attribute1
    EAttribute attribute1 = EcoreFactory.eINSTANCE.createEAttribute();
    attribute1.setName("attribute1");
    attribute1.setEType(EcorePackage.Literals.ESTRING);
    assertNotNull(attribute1.getEGenericType());
    assertEquals(EcorePackage.Literals.ESTRING, attribute1.getEGenericType().getEClassifier());

    eClass.getEStructuralFeatures().add(attribute1);

    assertEquals(asList(attribute1), eClass.getEAllStructuralFeatures());

    assertNotNull(eClass.getFeatureType(attribute1));
    assertEquals(EcorePackage.Literals.ESTRING, eClass.getFeatureType(attribute1).getEClassifier());

    // add attribute2
    EAttribute attribute2 = EcoreFactory.eINSTANCE.createEAttribute();
    attribute2.setName("attribute2");
    attribute2.setEType(EcorePackage.Literals.EINT);
    assertNotNull(attribute2.getEGenericType());
    assertEquals(EcorePackage.Literals.EINT, attribute2.getEGenericType().getEClassifier());

    eClass.getEStructuralFeatures().add(attribute2);

    assertEquals(asList(attribute1, attribute2), eClass.getEAllStructuralFeatures());

    assertNotNull(eClass.getFeatureType(attribute1));
    assertEquals(EcorePackage.Literals.ESTRING, eClass.getFeatureType(attribute1).getEClassifier());
    assertNotNull(eClass.getFeatureType(attribute2));
    assertEquals(EcorePackage.Literals.EINT, eClass.getFeatureType(attribute2).getEClassifier());

    // add attribute3
    EAttribute attribute3 = EcoreFactory.eINSTANCE.createEAttribute();
    attribute3.setName("attribute2");
    attribute3.setEType(EcorePackage.Literals.ELONG);
    assertNotNull(attribute3.getEGenericType());
    assertEquals(EcorePackage.Literals.ELONG, attribute3.getEGenericType().getEClassifier());

    eClass.getEStructuralFeatures().add(attribute3);

    assertEquals(asList(attribute1, attribute2, attribute3), eClass.getEAllStructuralFeatures());

    assertNotNull(eClass.getFeatureType(attribute1));
    assertEquals(EcorePackage.Literals.ESTRING, eClass.getFeatureType(attribute1).getEClassifier());
    assertNotNull(eClass.getFeatureType(attribute2));
    assertEquals(EcorePackage.Literals.EINT, eClass.getFeatureType(attribute2).getEClassifier());
    assertNotNull(eClass.getFeatureType(attribute3));
    assertEquals(EcorePackage.Literals.ELONG, eClass.getFeatureType(attribute3).getEClassifier());

    // remove attribute2
    eClass.getEStructuralFeatures().remove(attribute2);

    assertEquals(asList(attribute1, attribute3), eClass.getEAllStructuralFeatures());

    assertNotNull(eClass.getFeatureType(attribute1));
    assertEquals(EcorePackage.Literals.ESTRING, eClass.getFeatureType(attribute1).getEClassifier());
    assertNotNull(eClass.getFeatureType(attribute3));
    assertEquals(EcorePackage.Literals.ELONG, eClass.getFeatureType(attribute3).getEClassifier());

    // add attribute2 and remove attribute1
    eClass.getEStructuralFeatures().add(attribute2);
    eClass.getEStructuralFeatures().remove(attribute1);

    assertEquals(asList(attribute3, attribute2), eClass.getEAllStructuralFeatures());

    assertNotNull(eClass.getFeatureType(attribute3));
    assertEquals(EcorePackage.Literals.ELONG, eClass.getFeatureType(attribute3).getEClassifier());
    assertNotNull(eClass.getFeatureType(attribute2));
    assertEquals(EcorePackage.Literals.EINT, eClass.getFeatureType(attribute2).getEClassifier());
  }
}