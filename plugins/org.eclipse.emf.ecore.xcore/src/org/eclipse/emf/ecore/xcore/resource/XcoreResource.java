/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.resource;


import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;

import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xcore.XcorePackage;
import org.eclipse.emf.ecore.xcore.scoping.LazyCreationProxyURIConverter;
import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.common.types.JvmMember;
import org.eclipse.xtext.common.types.TypesPackage;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.util.Pair;
import org.eclipse.xtext.xbase.resource.BatchLinkableResource;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.inject.Inject;


public class XcoreResource extends BatchLinkableResource
{
  @Inject
  private LazyCreationProxyURIConverter proxyConverter;

  @Inject
  private IQualifiedNameProvider nameProvider;

  @Inject
  private IScopeProvider scopeProvider;

  private LinkedHashSet<Pair<EClass, QualifiedName>> resolving = Sets.newLinkedHashSet();

  protected class FragmentCache extends AdapterImpl
  {
    protected Map<String, EObject> map;
    protected int expectedSize;

    public FragmentCache(XcoreResource xcoreResource)
    {
      XcoreResource.this.eAdapters().add(this);
    }

    public EObject get(String uriFragment)
    {
      int actualSize = XcoreResource.this.getContents().size();
      if (map != null && expectedSize != actualSize)
      {
        clear();
      }

      if (map == null)
      {
        map = Maps.newHashMap();
        for (EObject eObject : XcoreResource.this.getContents())
        {
          buildEntry(eObject);
        }
        expectedSize = actualSize;
      }
      return map.get(uriFragment);
    }

    protected void buildEntry(EObject eObject)
    {
      eObject.eAdapters().add(this);
      map.put(getURIFragment(eObject), eObject);
      if (eObject instanceof GenModel)
      {
        for (GenPackage genPackage : ((GenModel)eObject).getGenPackages())
        {
          buildEntry(genPackage);
          for (GenClassifier genClassifier : genPackage.getGenClassifiers())
          {
            buildEntry(genClassifier);
          }
        }
      }
      else if (eObject instanceof EPackage)
      {
        for (EClassifier eClassifier : ((EPackage)eObject).getEClassifiers())
        {
          buildEntry(eClassifier);
        }
      }
      else if (eObject instanceof JvmDeclaredType)
      {
        for (JvmMember jvmMember : ((JvmDeclaredType)eObject).getMembers())
        {
          if (jvmMember instanceof JvmDeclaredType)
          {
            buildEntry(jvmMember);
          }
        }
      }
    }

    @Override
    public void notifyChanged(Notification notification)
    {
      if (map != null)
      {
        Object feature = notification.getFeature();
        if (feature == null ||
              feature == EcorePackage.Literals.ENAMED_ELEMENT__NAME ||
              feature == TypesPackage.Literals.JVM_MEMBER__SIMPLE_NAME ||
              feature == TypesPackage.Literals.JVM_DECLARED_TYPE__PACKAGE_NAME)
        {
          clear();
        }
      }
    }

    public void clear()
    {
      Collection<EObject> values = map.values();
      map = null;
      for (EObject eObject : values)
      {
        eObject.eAdapters().remove(this);
      }
    }
  }

  protected FragmentCache fragmentCache;

  @Override
  public synchronized EObject getEObject(String uriFragment)
  {
    if (fragmentCache == null)
    {
      fragmentCache = new FragmentCache(this);
    }

    EObject result = fragmentCache.get(uriFragment);
    if (result != null)
    {
      return result;
    }
    else
    {
      Pair<EClass, QualifiedName> fragmentInfo = proxyConverter.decodeFragment(uriFragment);
      if (fragmentInfo != null)
      {
        try
        {
          return
            resolving.add(fragmentInfo) ?
              findEObject(fragmentInfo.getFirst(), fragmentInfo.getSecond(), uriFragment) :
              null;
        }
        finally
        {
          resolving.remove(fragmentInfo);
        }
      }
      else
      {
        return super.getEObject(uriFragment);
      }
    }
  }

  /**
   * Finds the EObject of the given type and the given {@link QualifiedName}.
   */
  protected EObject findEObject(EClass eClass, QualifiedName name, String uriFragment)
  {
    if (eClass == TypesPackage.Literals.JVM_TYPE ||
        eClass == GenModelPackage.Literals.GEN_CLASS ||
        eClass == GenModelPackage.Literals.GEN_DATA_TYPE ||
        eClass == GenModelPackage.Literals.GEN_ENUM)
    {
      IScope scope =
        scopeProvider.getScope
          (getContents().get(0),
            eClass == TypesPackage.Literals.JVM_TYPE ?
             TypesPackage.Literals.JVM_PARAMETERIZED_TYPE_REFERENCE__TYPE :
             XcorePackage.Literals.XGENERIC_TYPE__TYPE);
      final IEObjectDescription eObjectDescription = scope.getSingleElement(name);
      if (eObjectDescription != null)
      {
        URI uri = eObjectDescription.getEObjectURI();
        if (!uriFragment.equals(uri.fragment()) || !uri.trimFragment().equals(getURI()))
        {
          return eObjectDescription.getEObjectOrProxy();
        }
      }
    }
    return null;
  }

  @Override
  public String getURIFragment(EObject object)
  {
    EClass eClass = object.eClass();
    if (eClass == TypesPackage.Literals.JVM_ENUMERATION_TYPE || eClass == GenModelPackage.Literals.GEN_CLASS
      || eClass == GenModelPackage.Literals.GEN_DATA_TYPE || eClass == GenModelPackage.Literals.GEN_ENUM
      || eClass == TypesPackage.Literals.JVM_GENERIC_TYPE)
    {
      QualifiedName qualifiedName = nameProvider.getFullyQualifiedName(object);
      if (qualifiedName != null)
      {
        return proxyConverter.encodeFragment(eClass, qualifiedName);
      }
    }
    return super.getURIFragment(object);
  }

  @Override
  protected String getURIFragmentRootSegment(EObject eObject)
  {
    if (eObject instanceof EPackage)
    {
      return "EPackage";
    }
    else
    {
      return super.getURIFragmentRootSegment(eObject);
    }
  }

  @Override
  protected EObject getEObjectForURIFragmentRootSegment(String uriFragmentRootSegment)
  {
    if ("EPackage".equals(uriFragmentRootSegment))
    {
      return (EObject)EcoreUtil.getObjectByType(getContents(), EcorePackage.Literals.EPACKAGE);
    }
    else
    {
      return super.getEObjectForURIFragmentRootSegment(uriFragmentRootSegment);
    }
  }

}