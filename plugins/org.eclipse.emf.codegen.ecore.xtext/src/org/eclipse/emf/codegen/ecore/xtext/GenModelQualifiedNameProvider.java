/**
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.codegen.ecore.xtext;


import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;

import com.google.inject.Inject;


/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class GenModelQualifiedNameProvider extends IQualifiedNameProvider.AbstractImpl
{
  @Inject
  private IQualifiedNameConverter qualifiedNameConverter;

  public QualifiedName getFullyQualifiedName(final EObject obj)
  {
    if (obj instanceof GenPackage)
    {
      String packageName = ((GenPackage)obj).getQualifiedPackageName();
      if (packageName != null)
      {
        return qualifiedNameConverter.toQualifiedName(packageName);
      }
    }
    else if (obj instanceof GenClassifier)
    {
      GenClassifier classifier = (GenClassifier)obj;
      GenPackage genPackage = classifier.getGenPackage();
      if (genPackage != null)
      {
        String packageName = genPackage.getQualifiedPackageName();
        String classifierName = classifier.getName();
        if (packageName != null && classifierName != null)
        {
          return qualifiedNameConverter.toQualifiedName(packageName).append(classifierName);
        }
      }
    }
    return null;
  }
}
