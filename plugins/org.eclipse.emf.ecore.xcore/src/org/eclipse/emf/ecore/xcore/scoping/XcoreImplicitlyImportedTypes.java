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
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xcore.lib.XcoreCollectionLiterals;
import org.eclipse.emf.ecore.xcore.lib.XcoreEListExtensions;
import org.eclipse.emf.ecore.xcore.lib.XcoreIterableExtensions;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.util.IResourceScopeCache;
import org.eclipse.xtext.xbase.scoping.batch.ImplicitlyImportedFeatures;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;


@Singleton
public class XcoreImplicitlyImportedTypes extends ImplicitlyImportedFeatures
{
  @Inject
  private IResourceScopeCache cache;

  @Override
  public List<JvmType> getExtensionClasses(final Resource context)
  {
    return
      cache.get
        ("extension.classes",
         context,
         new Provider<List<JvmType>>()
         {
           public List<JvmType> get()
           {
             return XcoreImplicitlyImportedTypes.super.getExtensionClasses(context);
           }
         });
  }

  @Override
  public List<JvmType> getStaticImportClasses(final Resource context)
  {
    return
      cache.get
        ("static.import.classes",
         context,
         new Provider<List<JvmType>>()
         {
           public List<JvmType> get()
           {
             return XcoreImplicitlyImportedTypes.super.getStaticImportClasses(context);
           }
         });
  }

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
