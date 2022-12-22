/**
 * Copyright (c) 2022 Eclipse Contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.test.core.dynamic;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

public class SimpleModelTest2 extends SimpleModelTest
{
  
  @Override
  public void setUp() throws Exception
  {
    super.setUp();
    companyPackage.setEFactoryInstance(new EFactoryImpl()
    {
      @Override
      protected EObject basicCreate(EClass eClass)
      {
        return eClass.getInstanceClassName() == "java.util.Map$Entry"
          ? new MinimalEObjectImpl.Container.Dynamic.BasicEMapEntry<String, String>(eClass) : new MinimalEObjectImpl.Container.Dynamic(eClass);
      }
    });
  }
}
