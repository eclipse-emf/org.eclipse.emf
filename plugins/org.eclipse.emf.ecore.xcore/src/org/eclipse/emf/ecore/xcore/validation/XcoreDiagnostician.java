/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.validation;

import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.validation.CancelableDiagnostician;

import com.google.inject.Inject;

public class XcoreDiagnostician extends CancelableDiagnostician
{
	@Inject
	public XcoreDiagnostician(EValidator.Registry registry)
  {
	  super(registry);
	  // TODO Auto-generated constructor stub
  }
	
	@Override
	public String getObjectLabel(EObject eObject)
	{
		if (eObject instanceof EPackage)
		{
			EPackage ePackage = (EPackage)eObject;
			String name = ePackage.getName();
			String basePackage = EcoreUtil.getAnnotation(ePackage, GenModelPackage.eNS_URI, "basePackage");
			if (basePackage != null)
			{
				name = basePackage + '.' + name;
			}
			return name;
		}
		else if (eObject instanceof ENamedElement)
		{
			String name = ((ENamedElement)eObject).getName();
			return getObjectLabel(eObject.eContainer()) + "." + name;
		}
		else if (eObject instanceof EGenericType)
		{
			StringBuilder result = new StringBuilder();
			new EcoreUtil.EGenericTypeConverter()
			{
				protected String getInstanceTypeName(EClassifier eClassifier) 
				{
					return getObjectLabel(eClassifier);
				}
			}.convertJavaInstanceTypeName(result, (EGenericType)eObject);
			return result.toString();
		}
		else
		{
	    return super.getObjectLabel(eObject);
		}
	}

}
