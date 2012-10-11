/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.scoping;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xcore.lib.XcoreCollectionLiterals;
import org.eclipse.emf.ecore.xcore.lib.XcoreEListExtensions;
import org.eclipse.emf.ecore.xcore.lib.XcoreIterableExtensions;
import org.eclipse.emf.ecore.xcore.lib.XcoreObjectsExtensions;
import org.eclipse.xtext.xbase.scoping.featurecalls.StaticImplicitMethodsFeatureForTypeProvider.ExtensionClassNameProvider;

import com.google.common.collect.Multimap;
import com.google.inject.Singleton;

@Singleton
public class XcoreExtensionClassNameProvider extends ExtensionClassNameProvider
{
  @Override
  protected Collection<String> computeLiteralClassNames()
  {
    Collection<String> result = super.computeLiteralClassNames();
    result.add(XcoreCollectionLiterals.class.getName());
    return result;
  }

  @Override
  protected Multimap<Class<?>, Class<?>> simpleComputeExtensionClasses()
  {
    Multimap<Class<?>, Class<?>> result = super.simpleComputeExtensionClasses();

    result.put(Object.class, XcoreObjectsExtensions.class);
    
    result.put(Iterable.class, ECollections.class);
    result.put(Iterable.class, XcoreIterableExtensions.class);

    result.put(Iterator.class, ECollections.class);
    
    result.put(List.class, ECollections.class);

    result.put(EMap.class, ECollections.class);

    result.put(EList.class, ECollections.class);
    result.put(EList.class, XcoreEListExtensions.class);

    result.put(EObject.class, EcoreUtil.class);
    result.put(EClass.class, EcoreUtil.class);
    result.put(EDataType.class, EcoreUtil.class);
    result.put(EModelElement.class, EcoreUtil.class);
    result.put(EGenericType.class, EcoreUtil.class);

    result.put(Notifier.class, EcoreUtil.class);
    result.put(Resource.class, EcoreUtil.class);
    result.put(ResourceSet.class, EcoreUtil.class);

    return result;
  }
}
