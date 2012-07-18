/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.scoping;


import java.util.Map;

import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.xtext.common.types.TypesPackage;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.util.Pair;
import org.eclipse.xtext.util.Tuples;

import com.google.inject.Inject;

import static com.google.common.collect.Maps.*;


public class LazyCreationProxyURIConverter
{
  @Inject
  private IQualifiedNameConverter nameConverter;

  private Map<String, EClass> types = newHashMap();
  {
    EClass genClass = GenModelPackage.Literals.GEN_CLASS;
    EClass genDatatype = GenModelPackage.Literals.GEN_DATA_TYPE;
    EClass genEnum = GenModelPackage.Literals.GEN_ENUM;
    EClass jvmGenericType = TypesPackage.Literals.JVM_GENERIC_TYPE;
    EClass jvmEnumerationType = TypesPackage.Literals.JVM_ENUMERATION_TYPE;
    EClass jvmType = TypesPackage.Literals.JVM_TYPE;
    types.put(genClass.getName(), genClass);
    types.put(genDatatype.getName(), genDatatype);
    types.put(genEnum.getName(), genEnum);
    types.put(jvmGenericType.getName(), jvmGenericType);
    types.put(jvmEnumerationType.getName(), jvmEnumerationType);
    types.put(jvmType.getName(), jvmType);
  }

  public void installProxyURI(URI resourceURI, EObject eobject, QualifiedName name)
  {
    URI proxyURI = getProxyURI(resourceURI, eobject, name);
    ((InternalEObject)eobject).eSetProxyURI(proxyURI);
  }

  public URI getProxyURI(URI resourceURI, EObject eObject, QualifiedName name)
  {
    if (!isSupported(eObject))
    {
      throw new IllegalArgumentException("eObjects of type " + eObject.eClass().getName() + " are not supported.");
    }
    return resourceURI.appendFragment(encodeFragment(eObject.eClass(), name));
  }

  protected boolean isSupported(EObject eObject)
  {
    return types.containsValue(eObject.eClass());
  }

  public Pair<EClass, QualifiedName> decodeProxy(EObject eObject)
  {
    URI proxyURI = ((InternalEObject)eObject).eProxyURI();
    if (proxyURI != null)
    {
      return decodeProxyURI(proxyURI);
    }
    else
    {
      throw new IllegalArgumentException("Not a proxy: " + eObject);
    }
  }

  public Pair<EClass, QualifiedName> decodeProxyURI(URI proxyURI)
  {
    final String fragment = proxyURI.fragment();
    if (fragment != null)
    {
      Pair<EClass, QualifiedName> fragmentInfo = decodeFragment(fragment);
      if (fragmentInfo != null)
      {
        return fragmentInfo;
      }
    }
    throw new IllegalArgumentException("No fragment: " + proxyURI);
  }

  protected static final String DELIM = "-=-";

  public String encodeFragment(EClass eClass, QualifiedName name)
  {
    return (eClass.getEPackage() == TypesPackage.eINSTANCE ? "JvmType" : eClass.getName()) + DELIM + name.toString();
  }

  public Pair<EClass, QualifiedName> decodeFragment(String fragment)
  {
    String[] segments = fragment.split(DELIM);
    if (segments.length == 2)
    {
      String eClassName = segments[0];
      QualifiedName name = nameConverter.toQualifiedName(segments[1]);
      if (types.containsKey(eClassName))
      {
        EClass eClass = types.get(eClassName);
        return Tuples.create(eClass, name);
      }
    }
    return null;
  }
}
