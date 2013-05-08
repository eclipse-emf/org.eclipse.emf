/**
 * Copyright (c) 2013 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.validation;

import org.eclipse.emf.ecore.xcore.XClassifier;
import org.eclipse.emf.ecore.xcore.XcorePackage;
import org.eclipse.xtext.common.types.JvmParameterizedTypeReference;
import org.eclipse.xtext.xbase.validation.JvmTypeReferencesValidator;


public class XcoreJvmTypeReferencesValidator extends JvmTypeReferencesValidator
{
  @Override
  public void checkTypeArgsAgainstTypeParameters(JvmParameterizedTypeReference typeRef)
  {
    if (typeRef.eContainmentFeature() != XcorePackage.Literals.XCLASSIFIER__INSTANCE_TYPE ||
         ((XClassifier)typeRef.eContainer()).getTypeParameters().isEmpty() && !"java.util.Map$Entry".equals(typeRef.getIdentifier()))
    {
      super.checkTypeArgsAgainstTypeParameters(typeRef);
    }
  }
}
