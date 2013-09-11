/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.scoping;


import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenTypeParameter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.xcore.XNamedElement;
import org.eclipse.emf.ecore.xcore.mappings.ToXcoreMapping;
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.xtext.common.types.JvmConstructor;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.JvmTypeParameter;
import org.eclipse.xtext.naming.DefaultDeclarativeQualifiedNameProvider;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.QualifiedName;

import com.google.inject.Inject;


public class XcoreQualifiedNameProvider extends DefaultDeclarativeQualifiedNameProvider
{
  @Inject
  private IQualifiedNameConverter nameConverter;

  @Inject 
  private XcoreMapper xcoreMapper;

  @Override
  public QualifiedName getFullyQualifiedName(EObject eObject)
  {
    if (eObject instanceof GenClassifier)
    {
      GenClassifier genClassifier = (GenClassifier)eObject;
      GenPackage genPackage = genClassifier.getGenPackage();
      if (genPackage != null)
      {
        String packageName = genPackage.getQualifiedPackageName();
        return nameConverter.toQualifiedName(packageName + "." + genClassifier.getName());
      }
      else
      {
        return null;
      }
    }
    else if (eObject instanceof GenTypeParameter)
    {
      String name = ((GenTypeParameter)eObject).getName();
      return name == null ? null : nameConverter.toQualifiedName(name);
    }
    else if (eObject instanceof JvmConstructor)
    {
      JvmConstructor constructor = (JvmConstructor)eObject;
      String typeName = constructor.getQualifiedName();
      return typeName == null ? null : nameConverter.toQualifiedName(typeName);
    }
    else if (eObject instanceof JvmType && !(eObject instanceof JvmTypeParameter))
    {
      JvmType type = (JvmType)eObject;
      String typeName = type.getQualifiedName();
      return typeName == null ? null : nameConverter.toQualifiedName(typeName);
    }
    else if (eObject instanceof EPackage)
    {
      ToXcoreMapping toXcoreMapping = xcoreMapper.getToXcoreMapping(eObject);
      XNamedElement xNamedElement = toXcoreMapping.getXcoreElement();
      if (xNamedElement != null)
      {
        String packageName = xNamedElement.getName();
        return packageName == null ? null : QualifiedName.create(packageName);
      }
      else
      {
        return null;
      }
    }
    else
    {
      return super.getFullyQualifiedName(eObject);
    }
  }

}
