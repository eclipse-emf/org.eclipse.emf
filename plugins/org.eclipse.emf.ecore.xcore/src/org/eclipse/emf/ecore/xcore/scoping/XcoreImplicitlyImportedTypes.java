/**
 * Copyright (c) 2013 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.scoping;


import java.util.List;

import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xcore.lib.XcoreCollectionLiterals;
import org.eclipse.emf.ecore.xcore.lib.XcoreEListExtensions;
import org.eclipse.emf.ecore.xcore.lib.XcoreIterableExtensions;
import org.eclipse.xtext.xbase.scoping.batch.ImplicitlyImportedTypes;

import com.google.inject.Singleton;


@Singleton
public class XcoreImplicitlyImportedTypes extends ImplicitlyImportedTypes
{
  @Override
  protected List<Class<?>> getExtensionClasses()
  {
    List<Class<?>> extensionClasses = super.getExtensionClasses();
    extensionClasses.add(ECollections.class);
    extensionClasses.add(XcoreIterableExtensions.class);
    extensionClasses.add(XcoreEListExtensions.class);
    extensionClasses.add(EcoreUtil.class);
    return extensionClasses;
  }

  @Override
  protected List<Class<?>> getStaticImportClasses()
  {
    List<Class<?>> result = super.getStaticImportClasses();
    result.add(XcoreCollectionLiterals.class);
    return result;
  }
}
