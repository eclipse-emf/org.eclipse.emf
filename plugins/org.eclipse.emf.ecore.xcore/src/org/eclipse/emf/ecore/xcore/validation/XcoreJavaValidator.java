/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.validation;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.xcore.XcorePackage;
import org.eclipse.xtext.common.types.TypesPackage;
import org.eclipse.xtext.xbase.XbasePackage;
import org.eclipse.xtext.xtype.XtypePackage;


public class XcoreJavaValidator extends AbstractXcoreJavaValidator
{

  @Override
  protected List<EPackage> getEPackages()
  {
    List<EPackage> result = new ArrayList<EPackage>();
    result.add(XcorePackage.eINSTANCE);
    result.add(XbasePackage.eINSTANCE);
    result.add(TypesPackage.eINSTANCE);
    result.add(XtypePackage.eINSTANCE);
    return result;
  }

  // @Check
  // public void checkGreetingStartsWithCapital(Greeting greeting) {
  // if (!Character.isUpperCase(greeting.getName().charAt(0))) {
  // warning("Name should start with a capital",
  // MyDslPackage.Literals.GREETING__NAME);
  // }
  // }

}
